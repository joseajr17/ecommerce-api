package com.ecommerceapi.api.controllers;

import com.ecommerceapi.api.domain.cart.AddToCartRequestDTO;
import com.ecommerceapi.api.domain.cart.CartResponseDTO;
import com.ecommerceapi.api.domain.cartItem.CartItem;
import com.ecommerceapi.api.domain.cartItem.UpdateItemRequestDTO;
import com.ecommerceapi.api.repositories.UserRepository;
import com.ecommerceapi.api.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<CartResponseDTO> addProductToCart(@AuthenticationPrincipal UserDetails userDetails, @RequestBody AddToCartRequestDTO data) {
        String userEmail = userDetails.getUsername();

        UUID userId = userRepository.findUserIdByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return ResponseEntity.ok(cartService.addProductToCart(userId, data));
    }

    @GetMapping("/get")
    public ResponseEntity<CartResponseDTO> getCart(@AuthenticationPrincipal UserDetails userDetails) {
        String userEmail = userDetails.getUsername();

        UUID userId = userRepository.findUserIdByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @PutMapping("/items/{cartItemId}")
    public ResponseEntity<CartResponseDTO> updateItemQuantity(@PathVariable UUID cartItemId, @RequestBody UpdateItemRequestDTO body) {
        return ResponseEntity.ok(cartService.updateItemQuantity(cartItemId, body));
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable UUID cartItemId) {
        cartService.removeItemFromCart(cartItemId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<CartItem> clearCart(@AuthenticationPrincipal UserDetails userDetails) {
        String userEmail = userDetails.getUsername();

        UUID userId = this.userRepository.findUserIdByEmail(userEmail)
                        .orElseThrow(() -> new IllegalArgumentException("User not found"));

        cartService.clearCart(userId);

        return ResponseEntity.noContent().build();
    }




}
