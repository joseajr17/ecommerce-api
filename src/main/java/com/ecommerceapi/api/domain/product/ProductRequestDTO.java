package com.ecommerceapi.api.domain.product;

import com.ecommerceapi.api.domain.category.Category;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequestDTO(String name, String description, BigDecimal price, Integer stock, UUID categoryId) {
}