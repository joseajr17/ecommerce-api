package com.ecommerceapi.api.domain.cartItem;

import com.ecommerceapi.api.domain.cart.Cart;
import com.ecommerceapi.api.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "cart_item")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue
    private UUID id;
    private int quantity;

    // Vários itens podem pertencer a um carrinho
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    // Um item está associado a um único produto
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
