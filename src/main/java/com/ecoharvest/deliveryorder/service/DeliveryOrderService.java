package com.ecoharvest.deliveryorder.service;

import com.ecoharvest.deliveryorder.domain.DeliveryOrder;
import com.ecoharvest.deliveryorder.domain.Item;
import com.ecoharvest.deliveryorder.repository.DeliveryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryOrderService {

    @Autowired
    private DeliveryOrderRepository deliveryOrderRepository;

    public DeliveryOrder create(DeliveryOrder deliveryOrder) {
        for (Item item : deliveryOrder.getItems()) {
            item.setDeliveryOrder(deliveryOrder);
        }
        return deliveryOrderRepository.save(deliveryOrder);
    }

    public List<DeliveryOrder> findAll() {
        return deliveryOrderRepository.findAll();
    }

    public Optional<DeliveryOrder> findById(Long id) {
        return deliveryOrderRepository.findById(id);
    }

    public List<DeliveryOrder> findAllByUserName(String userName) {
        return deliveryOrderRepository.findAllByUserName(userName);
    }

    public DeliveryOrder update(Long id, DeliveryOrder updatedDeliveryOrder) {
        Optional<DeliveryOrder> deliveryOrderOptional = findById(id);
        if (deliveryOrderOptional.isEmpty()) {
            return null;
        }
        DeliveryOrder deliveryOrder = deliveryOrderOptional.get();
        deliveryOrder.getItems().clear();
        for (Item item : updatedDeliveryOrder.getItems()) {
            item.setDeliveryOrder(deliveryOrder);
            deliveryOrder.getItems().add(item);
        }
        deliveryOrder.setUserName(updatedDeliveryOrder.getUserName());
        deliveryOrder.setAddress(updatedDeliveryOrder.getAddress());
        deliveryOrder.setCreatedTimestamp(updatedDeliveryOrder.getCreatedTimestamp());
        deliveryOrder.setTimeslot(updatedDeliveryOrder.getTimeslot());
        deliveryOrder.setDeliveryStatus(updatedDeliveryOrder.getDeliveryStatus());
        DeliveryOrder savedDeliveryOrder = save(deliveryOrder);
        return savedDeliveryOrder;
    }

    public DeliveryOrder updateDeliveryOrderStatus(Long id, String deliveryStatus) {
        Optional<DeliveryOrder> deliveryOrderOptional = findById(id);
        if (deliveryOrderOptional.isEmpty()) {
            return null;
        }
        DeliveryOrder deliveryOrder = deliveryOrderOptional.get();
        deliveryOrder.setDeliveryStatus(deliveryStatus);
        DeliveryOrder savedDeliveryOrder = save(deliveryOrder);
        return savedDeliveryOrder;
    }

    public DeliveryOrder save(DeliveryOrder deliveryOrder) {
        return deliveryOrderRepository.save(deliveryOrder);
    }

    public boolean deleteById(Long id) {
        Optional<DeliveryOrder> deliveryOrderOptional = findById(id);
        if (deliveryOrderOptional.isEmpty()) {
            return false;
        }
        deliveryOrderRepository.deleteById(id);
        return true;
    }

}
