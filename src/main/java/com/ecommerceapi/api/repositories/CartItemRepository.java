package com.ecommerceapi.api.repositories;

import com.ecommerceapi.api.domain.cart.Cart;
import com.ecommerceapi.api.domain.cartItem.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {

    @Query("SELECT ci FROM CartItem ci " +
            "LEFT JOIN ci.cart c " +
            "LEFT JOIN ci.product p " +
            "WHERE c.id = :cart_id " +
            "AND p.id = :product_id")
    Optional<CartItem> findByCartIdAndProductId(@Param("cart_id") UUID cartId,
                                                @Param("product_id") UUID productId);

    @Modifying
    @Query("DELETE FROM CartItem ci WHERE ci.cart.id = :cartId")
    void deleteByCartId(@Param("cartId") UUID cartId);
}
