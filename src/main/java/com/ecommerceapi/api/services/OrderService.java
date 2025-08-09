package com.ecommerceapi.api.services;

import com.ecommerceapi.api.domain.cart.Cart;
import com.ecommerceapi.api.domain.cartItem.CartItem;
import com.ecommerceapi.api.domain.order.Order;
import com.ecommerceapi.api.dtos.order.OrderResponseDTO;
import com.ecommerceapi.api.dtos.order.OrderStatusRequestDTO;
import com.ecommerceapi.api.domain.orderItem.OrderItem;
import com.ecommerceapi.api.dtos.orderItem.OrderItemResponseDTO;
import com.ecommerceapi.api.domain.payment.Payment;
import com.ecommerceapi.api.dtos.payment.PaymentResponseDTO;
import com.ecommerceapi.api.domain.product.Product;
import com.ecommerceapi.api.domain.user.User;
import com.ecommerceapi.api.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional
    public OrderResponseDTO checkout(UUID userId) {

        // Verifica se o carrinho do usuário tem itens.
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        List<CartItem> cartItems = cart.getCartItems();
        if(cartItems.isEmpty()){
          throw new IllegalArgumentException("Cart's empty");
        }

        // Cria um Order com status PENDING e createdAt.
        Order order = createOrder(userId);

        // Converte CartItem em OrderItem, salvando o preço do produto como unitPrice e atualiza o estoque dos produtos.
        List<OrderItem> orderItems = createOrderItems(cartItems, order);

        // Calcular totalAmount
        BigDecimal totalAmount = orderItems.stream()
                .map(orderItem -> orderItem.getUnitPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalAmount(totalAmount);
        order.setOrderItemList(orderItems);

        orderRepository.save(order);

        // Cria um Payment com status COMPLETED.
        Payment payment = createPayment(order, totalAmount);

        // Define o status do pedido como PAID
        order.setStatus("PAID");
        orderRepository.save(order);

        // Limpar carrinho
        cartItemRepository.deleteByCartId(cart.getId());

        return toOrderResponseDTO(order);
    }

    // Cria um Order com status PENDING e createdAt.
    private Order createOrder(UUID userId) {
        Order order = new Order();
        order.setUser(new User(userId));
        order.setStatus("PENDING");
        order.setCreatedAt(new Date());
        return order;
    }

    // Criar OrderItems com snapshot dos preços
    private List<OrderItem> createOrderItems(List<CartItem> cartItems, Order order) {
        return cartItems.stream()
                .map(cartItem -> {
                    Product product = cartItem.getProduct();
                    if (product.getStock() < cartItem.getQuantity()) {
                        throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
                    }

                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setProduct(product);
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setUnitPrice(product.getPrice());

                    product.setStock(product.getStock() - cartItem.getQuantity());
                    productRepository.save(product);

                    return orderItem;
                }).collect(Collectors.toList());
    }

    // Criar Payment
    private Payment createPayment(Order order, BigDecimal totalAmount) {
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentDate(new Date());
        payment.setStatus("COMPLETED");
        payment.setAmount(totalAmount);

        paymentRepository.save(payment);

        return payment;
    }

    public List<OrderResponseDTO> getUserOrders(UUID userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(this::toOrderResponseDTO)
                .collect(Collectors.toList());
    }

    public OrderResponseDTO getOrderDetails(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        return toOrderResponseDTO(order);
    }

    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::toOrderResponseDTO)
                .collect(Collectors.toList());
    }

    public OrderResponseDTO updateOrderStatus(UUID orderId, OrderStatusRequestDTO data) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        String status = data.status().toUpperCase();
        if (!List.of("PENDING", "PAID", "SHIPPED", "DELIVERED", "FAILED").contains(status)) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
        order.setStatus(status);

        orderRepository.save(order);

        return toOrderResponseDTO(order);
    }



    public PaymentResponseDTO getPaymentByOrderId(UUID orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        return toPaymentResponseDTO(payment);
    }

    private PaymentResponseDTO toPaymentResponseDTO(Payment payment) {
        return new PaymentResponseDTO(
                payment.getId(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getPaymentDate()
        );
    }

    private OrderResponseDTO toOrderResponseDTO(Order order) {
        Payment payment = paymentRepository.findByOrderId(order.getId()).orElse(null);
        return new OrderResponseDTO(
                order.getId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getUser().getId(),
                order.getOrderItemList().stream()
                        .map(item -> new OrderItemResponseDTO(
                                item.getId(),
                                item.getQuantity(),
                                item.getUnitPrice(),
                                item.getProduct().getId(),
                                item.getProduct().getName()
                        ))
                        .collect(Collectors.toList()),
                payment != null ? new PaymentResponseDTO(
                        payment.getId(),
                        payment.getAmount(),
                        payment.getStatus(),
                        payment.getPaymentDate()
                ) : null
        );
    }
}
