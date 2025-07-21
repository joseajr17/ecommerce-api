package com.ecommerceapi.api.domain.cart;

import java.util.UUID;

public record AddToCartRequestDTO(UUID product_id, int quantity) {
}
