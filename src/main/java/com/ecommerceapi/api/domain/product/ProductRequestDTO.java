package com.ecommerceapi.api.domain.product;

import java.math.BigDecimal;

public record ProductRequestDTO(String name, String description, BigDecimal price, String category, int stock) {
}