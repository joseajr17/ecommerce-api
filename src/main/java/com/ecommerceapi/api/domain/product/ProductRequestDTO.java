package com.ecommerceapi.api.domain.product;

import com.ecommerceapi.api.domain.category.Category;

import java.math.BigDecimal;

public record ProductRequestDTO(String name, String description, BigDecimal price, Category category, int stock) {
}