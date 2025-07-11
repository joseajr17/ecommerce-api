package com.ecommerceapi.api.domain.product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO(UUID id, String name, String description, BigDecimal price, String category, int stock) {
}
