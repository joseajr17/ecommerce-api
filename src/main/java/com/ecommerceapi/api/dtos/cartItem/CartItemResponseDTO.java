package com.ecommerceapi.api.dtos.cartItem;

import java.math.BigDecimal;
import java.util.UUID;

public record CartItemResponseDTO(UUID id, int quantity, UUID productId, String productName, BigDecimal productPrice
) {}
