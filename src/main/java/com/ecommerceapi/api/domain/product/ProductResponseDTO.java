package com.ecommerceapi.api.domain.product;

import com.ecommerceapi.api.domain.category.Category;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO(UUID id, String name, String description, BigDecimal price, Category category, int stock) {
}
