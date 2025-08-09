package com.ecommerceapi.api.dtos.cart;

import java.util.UUID;

public record AddToCartRequestDTO(UUID product_id, int quantity) {
}
