package com.ecommerceapi.api.dtos.user;

import com.ecommerceapi.api.domain.user.UserRole;

public record RegisterDTO(String username, String email, String password, UserRole role) {
}
