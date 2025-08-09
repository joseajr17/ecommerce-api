package com.ecommerceapi.api.dtos.product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequestDTO(String name, String description, BigDecimal price, Integer stock, UUID categoryId) {
}