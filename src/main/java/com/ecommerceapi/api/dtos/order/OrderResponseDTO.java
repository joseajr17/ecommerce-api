package com.ecommerceapi.api.dtos.order;

import com.ecommerceapi.api.dtos.orderItem.OrderItemResponseDTO;
import com.ecommerceapi.api.dtos.payment.PaymentResponseDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public record OrderResponseDTO(UUID id, BigDecimal totalAmount, String status, Date createdAt, UUID userId, List<OrderItemResponseDTO> items, PaymentResponseDTO payment) {}