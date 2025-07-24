package com.ecommerceapi.api.controllers;

import com.ecommerceapi.api.domain.order.Order;
import com.ecommerceapi.api.domain.order.OrderResponseDTO;
import com.ecommerceapi.api.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<OrderResponseDTO> checkout(@RequestParam UUID userId) {
        return ResponseEntity.ok(orderService.checkout(userId));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getUserOrders(@RequestParam UUID userId) {
        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }

    @GetMapping("/admin")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
