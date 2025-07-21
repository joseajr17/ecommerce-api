package com.ecommerceapi.api.controllers;

import com.ecommerceapi.api.domain.cart.AddToCartRequestDTO;
import com.ecommerceapi.api.domain.cart.CartResponseDTO;
import com.ecommerceapi.api.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    // Autenticação: O userId está sendo passado como @RequestParam para simplicidade. Em um sistema com autenticação (ex.: Spring Security), obtenha o userId do contexto de segurança
    // UUID userId = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());
    @PostMapping("/add")
    public ResponseEntity<CartResponseDTO> addToCart(@RequestParam UUID userId, @RequestBody AddToCartRequestDTO data) {
        return ResponseEntity.ok(cartService.addToCart(userId, data));
    }
}
