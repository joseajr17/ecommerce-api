package com.ecommerceapi.api.domain.payment;

import com.ecommerceapi.api.domain.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Table(name = "payment")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue
    private UUID id;
    private BigDecimal amount;
    private String status;
    private Date paymentDate;

    // Um pagamento pertence a um pedido
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
