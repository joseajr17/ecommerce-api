package com.ecommerceapi.api.repositories;

import com.ecommerceapi.api.domain.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    @Query("SELECT p FROM Payment p " +
            "LEFT JOIN p.order o " +
            "WHERE o.id = :order_id")
    Optional<Payment> findByOrderId(@Param("order_id") UUID orderId);
}
