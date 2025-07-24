package com.ecommerceapi.api.repositories;

import com.ecommerceapi.api.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query("SELECT o FROM Order o " +
            "LEFT JOIN o.user u "+
            "WHERE u.id = :user_id")
    List<Order> findByUserId(@Param("user_id") UUID userId);
}
