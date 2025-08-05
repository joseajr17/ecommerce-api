package com.ecommerceapi.api.controllers;

import com.ecommerceapi.api.domain.order.OrderResponseDTO;
import com.ecommerceapi.api.domain.order.OrderStatusRequestDTO;
import com.ecommerceapi.api.domain.payment.PaymentResponseDTO;
import com.ecommerceapi.api.repositories.UserRepository;
import com.ecommerceapi.api.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(@PathVariable UUID orderId, @RequestBody OrderStatusRequestDTO body) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, body));
    }
}
