package one.digitalinnovation.patterns.service;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.patterns.domain.*;
import one.digitalinnovation.patterns.repository.CustomerRepository;
import one.digitalinnovation.patterns.repository.OrderRepository;
import one.digitalinnovation.patterns.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));
    }

    public Order createOrder(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        Order order = Order.builder()
                .customer(customer)
                .status(OrderStatus.RECEIVED)
                .total(BigDecimal.ZERO)
                .createdAt(LocalDateTime.now())
                .items(new ArrayList<>())
                .build();
        return orderRepository.save(order);
    }

    public Order addItemToOrder(Long orderId, Long productId, Integer quantity) {
        Order order = findById(orderId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        if (quantity == null || quantity <= 0) {
            throw new RuntimeException("A quantidade deve ser maior que zero.");
        }

        BigDecimal subTotal = product.getPrice().multiply(BigDecimal.valueOf(quantity));

        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(quantity)
                .unitPrice(product.getPrice())
                .subTotal(subTotal)
                .build();
        order.getItems().add(orderItem);
        recalculateOrderTotal(order);

        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = findById(orderId);
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    private void recalculateOrderTotal(Order order) {
        BigDecimal total = order.getItems().stream()
                .map(OrderItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotal(total);
    }
}
