package com.ecommerceapi.api.services;

import com.ecommerceapi.api.domain.cart.AddToCartRequestDTO;
import com.ecommerceapi.api.domain.cart.Cart;
import com.ecommerceapi.api.domain.cart.CartResponseDTO;
import com.ecommerceapi.api.domain.cartItem.CartItem;
import com.ecommerceapi.api.domain.cartItem.CartItemResponseDTO;
import com.ecommerceapi.api.domain.product.Product;
import com.ecommerceapi.api.domain.user.User;
import com.ecommerceapi.api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public CartResponseDTO addProductToCart(UUID userId, AddToCartRequestDTO data) {
        if (data.quantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        Product product = this.productRepository.findById(data.product_id())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if(product.getStock() < data.quantity()) {
            throw new IllegalArgumentException("Insufficient stock");
        }

        // Criar um carrinho vazio caso o usuário não tenha um
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(()-> {
                    Cart newCart = new Cart();
                    newCart.setUser(new User(userId));
                    return cartRepository.save(newCart);
                });

        // Add item caso não esteja no carrinho
        CartItem cartItem = this.cartItemRepository.findByCartIdAndProductId(cart.getId(), data.product_id())
                .orElseGet(() -> {
                    CartItem newCartItem = new CartItem();
                    newCartItem.setCart(cart);
                    newCartItem.setProduct(product);
                    newCartItem.setQuantity(0);
                    return cartItemRepository.save(newCartItem);
                });

        cartItem.setQuantity(cartItem.getQuantity() + data.quantity());
        cartItemRepository.save(cartItem);

        return toCartResponseDTO(cart);
    }

    public CartResponseDTO getCart(UUID userId) {
        // Criar um carrinho vazio caso o usuário não tenha um
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(()-> {
                    Cart newCart = new Cart();
                    newCart.setUser(new User(userId));
                    return cartRepository.save(newCart);
                });

        return toCartResponseDTO(cart);
    }

    private CartResponseDTO toCartResponseDTO(Cart cart) {
        BigDecimal total = cart.getCartItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CartResponseDTO(
                cart.getId(),
                cart.getUser().getId(),
                cart.getCartItems().stream()
                        .map(item -> new CartItemResponseDTO(
                                item.getId(),
                                item.getQuantity(),
                                item.getProduct().getId(),
                                item.getProduct().getName(),
                                item.getProduct().getPrice()
                        ))
                        .collect(Collectors.toList()),
                total
        );
    }

}
