package com.ecommerceapi.api.dtos.product;

import com.ecommerceapi.api.dtos.category.CategoryResponseDTO;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO(UUID id, String name, String description, BigDecimal price, CategoryResponseDTO category, int stock) {
}
