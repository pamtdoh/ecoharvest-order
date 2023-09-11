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

    @GetMapping("/history/{userName}")
    public ResponseEntity<List<DeliveryOrder>> findAllByUserName(@PathVariable String userName) {
        List<DeliveryOrder> deliveryOrder = deliveryOrderRepository.findAllByUserName(userName);
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
        if (deliveryOrderOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        DeliveryOrder deliveryOrder = deliveryOrderOptional.get();
        deliveryOrder.setItems(updatedDeliveryOrder.getItems());
        deliveryOrder.setUserName(updatedDeliveryOrder.getUserName());
        deliveryOrder.setAddress(updatedDeliveryOrder.getAddress());
        deliveryOrder.setTimeslot(updatedDeliveryOrder.getTimeslot());
        deliveryOrder.setDeliveryStatus(updatedDeliveryOrder.getDeliveryStatus());
        DeliveryOrder savedDeliveryOrder = deliveryOrderRepository.save(deliveryOrder);
        return ResponseEntity.ok(savedDeliveryOrder);
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
