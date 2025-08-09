package com.ecommerceapi.api.dtos.orderItem;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemResponseDTO(UUID id, int quantity, BigDecimal unitPrice, UUID productId, String productName) {}
