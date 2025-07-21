package com.ecommerceapi.api.repositories;

import com.ecommerceapi.api.domain.cart.Cart;
import com.ecommerceapi.api.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {

    @Query("SELECT c FROM Cart c " +
            "LEFT JOIN c.user u " +
            "WHERE u.id = :user_id")
    Optional<Cart> findByUserId(@Param("user_id") UUID userId);

}