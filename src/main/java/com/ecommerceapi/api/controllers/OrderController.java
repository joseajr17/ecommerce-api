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
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/orders/checkout")
    public ResponseEntity<OrderResponseDTO> checkout(@AuthenticationPrincipal UserDetails userDetails) {
        String userEmail = userDetails.getUsername();

        UUID userId = this.userRepository.findUserIdByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return ResponseEntity.ok(orderService.checkout(userId));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDTO>> getUserOrders(@AuthenticationPrincipal UserDetails userDetails) {
        String userEmail = userDetails.getUsername();

        UUID userId = this.userRepository.findUserIdByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderDetails(@PathVariable UUID orderId) {
        return ResponseEntity.ok(orderService.getOrderDetails(orderId));
    }

    @GetMapping("/admin/orders")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/admin/orders/{orderId}")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(@PathVariable UUID orderId, @RequestBody OrderStatusRequestDTO body) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, body));
    }

    @GetMapping("/payment/{orderId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentByOrderId(@PathVariable UUID orderId) {
        return ResponseEntity.ok(orderService.getPaymentByOrderId(orderId));
    }
}
