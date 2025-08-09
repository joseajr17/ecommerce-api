package com.ecommerceapi.api.dtos.cart;

import com.ecommerceapi.api.dtos.cartItem.CartItemResponseDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CartResponseDTO(UUID id, UUID userId, List<CartItemResponseDTO> items, BigDecimal total) {}


