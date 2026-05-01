package one.digitalinnovation.patterns.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import one.digitalinnovation.patterns.domain.Order;
import one.digitalinnovation.patterns.domain.OrderStatus;

public record OrderResponse(
    Long id,
    CustomerResponse customer,
    OrderStatus status,
    BigDecimal total,
    LocalDateTime createdAt,
    List<OrderItemResponse> items
) {
public static OrderResponse from(Order order) {
    return new OrderResponse(
            order.getOrderId(),
            CustomerResponse.from(order.getCustomer()),
            order.getStatus(),
            order.getTotal(),
            order.getCreatedAt(),
            order.getItems().stream()
                    .map(OrderItemResponse::from)
                    .toList()
    );
}
}