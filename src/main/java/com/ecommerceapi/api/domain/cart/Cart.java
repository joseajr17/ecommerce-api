package com.ecommerceapi.api.domain.cart;

import com.ecommerceapi.api.domain.cartItem.CartItem;
import com.ecommerceapi.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Table(name = "cart")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue
    private UUID id;

    // Vários carrinhos podem pertencer a um único usuário
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Um carrinho pode ter vários itens
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;
}
