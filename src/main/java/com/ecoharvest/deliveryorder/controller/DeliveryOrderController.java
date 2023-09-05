package com.ecoharvest.deliveryorder.controller;

import com.ecoharvest.deliveryorder.domain.DeliveryOrder;
import com.ecoharvest.deliveryorder.repository.DeliveryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/delivery-order")
public class DeliveryOrderController {
    @Autowired
    private DeliveryOrderRepository deliveryOrderRepository;

    @GetMapping
    public List<DeliveryOrder> findAll() {
        return deliveryOrderRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DeliveryOrder>> findOne(@PathVariable Long id) {
        Optional<DeliveryOrder> deliveryOrder = deliveryOrderRepository.findById(id);
        return ResponseEntity.ok(deliveryOrder);
    }

    @PostMapping("/add")
    public ResponseEntity<DeliveryOrder> create(@RequestBody DeliveryOrder deliveryOrder) {
        DeliveryOrder savedDeliveryOrder = deliveryOrderRepository.save(deliveryOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDeliveryOrder);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<DeliveryOrder> update(@PathVariable Long id, @RequestBody DeliveryOrder updatedDeliveryOrder) {
        Optional<DeliveryOrder> deliveryOrderOptional = deliveryOrderRepository.findById(id);
        if (deliveryOrderOptional.isPresent()) {
            DeliveryOrder deliveryOrder = deliveryOrderOptional.get();
            deliveryOrder.setOrderList(updatedDeliveryOrder.getOrderList());
            deliveryOrder.setAddress(updatedDeliveryOrder.getAddress());
            deliveryOrder.setUserName(updatedDeliveryOrder.getUserName());
            DeliveryOrder savedDeliveryOrder = deliveryOrderRepository.save(deliveryOrder);
            return ResponseEntity.ok(savedDeliveryOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<DeliveryOrder> deliveryOrderOptional = deliveryOrderRepository.findById(id);
        if (deliveryOrderOptional.isPresent()) {
            deliveryOrderRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
