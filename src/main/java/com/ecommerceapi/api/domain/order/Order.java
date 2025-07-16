package com.ecommerceapi.api.domain.order;

import com.ecommerceapi.api.domain.orderItem.OrderItem;
import com.ecommerceapi.api.domain.payment.Payment;
import com.ecommerceapi.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Table(name = "orders")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    private UUID id;
    private BigDecimal totalAmount;
    private String status;
    private Date createdAt;

    // Um pedido pertence a um único usuário
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Um pedido pode ter vários itens de pedido
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;

    // Um pedido pertence a um único pagamento
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;
}
