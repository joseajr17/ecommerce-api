package com.ecommerceapi.api.domain.orderItem;

import com.ecommerceapi.api.domain.order.Order;
import com.ecommerceapi.api.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "order_item")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue
    private UUID id;
    private int quantity;
    private BigDecimal unitPrice;

    // Um item de pedido pertence a um único produto
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Um item de pedido pertence a um pedido
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
