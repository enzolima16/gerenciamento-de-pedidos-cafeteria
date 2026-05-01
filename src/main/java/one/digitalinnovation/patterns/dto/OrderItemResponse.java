package one.digitalinnovation.patterns.dto;

import java.math.BigDecimal;

import one.digitalinnovation.patterns.domain.OrderItem;

public record OrderItemResponse(
    Long id,
    Long productId,
    String productName,
    Integer quantity,
    BigDecimal unitPrice,
    BigDecimal subTotal
) {
public static OrderItemResponse from(OrderItem item) {
    return new OrderItemResponse(
            item.getOrderItemId(),
            item.getProduct().getProductId(),
            item.getProduct().getName(),
            item.getQuantity(),
            item.getUnitPrice(),
            item.getSubTotal()
    );
}
}