package one.digitalinnovation.patterns.service;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.patterns.domain.*;
import one.digitalinnovation.patterns.dto.OrderItemRequest;
import one.digitalinnovation.patterns.dto.OrderResponse;
import one.digitalinnovation.patterns.exception.ResourceNotFoundException;
import one.digitalinnovation.patterns.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(OrderResponse::from)
                .toList();
    }

    public OrderResponse findById(Long id) {
        return OrderResponse.from(getOrderOrThrow(id));
    }

    public OrderResponse createOrder(Long customerId) {
        Customer customer = customerService.getCustomerOrThrow(customerId);
        Order order = Order.builder()
                .customer(customer)
                .status(OrderStatus.RECEIVED)
                .total(BigDecimal.ZERO)
                .createdAt(LocalDateTime.now())
                .items(new ArrayList<>())
                .build();
        return OrderResponse.from(orderRepository.save(order));
    }

    public OrderResponse addItemToOrder(Long orderId, OrderItemRequest request) {
        Order order = getOrderOrThrow(orderId);
        Product product = productService.getProductOrThrow(request.productId());

        BigDecimal subTotal = product.getPrice().multiply(BigDecimal.valueOf(request.quantity()));

        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(request.quantity())
                .unitPrice(product.getPrice())
                .subTotal(subTotal)
                .build();

        order.getItems().add(orderItem);
        recalculateOrderTotal(order);
        return OrderResponse.from(orderRepository.save(order));
    }

    public OrderResponse updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = getOrderOrThrow(orderId);
        order.setStatus(newStatus);
        return OrderResponse.from(orderRepository.save(order));
    }

    private Order getOrderOrThrow(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
    }

    private void recalculateOrderTotal(Order order) {
        BigDecimal total = order.getItems().stream()
                .map(OrderItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotal(total);
    }
}