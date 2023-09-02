package com.ecoharvest.deliveryorder.controller;

import com.ecoharvest.deliveryorder.domain.DeliveryOrder;
import com.ecoharvest.deliveryorder.repository.DeliveryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<DeliveryOrder> findOne(@PathVariable Long id) {
        return deliveryOrderRepository.findById(id);
    }

    @PostMapping
    public DeliveryOrder create(@RequestBody DeliveryOrder deliveryOrder) {
        return deliveryOrderRepository.save(deliveryOrder);
    }
}
