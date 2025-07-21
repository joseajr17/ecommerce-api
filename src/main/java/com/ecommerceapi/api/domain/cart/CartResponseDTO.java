package com.ecommerceapi.api.domain.cart;

import com.ecommerceapi.api.domain.cartItem.CartItemResponseDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CartResponseDTO(UUID id, UUID userId, List<CartItemResponseDTO> items, BigDecimal total) {}


