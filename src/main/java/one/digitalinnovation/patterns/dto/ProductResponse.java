package one.digitalinnovation.patterns.dto;

import java.math.BigDecimal;

import one.digitalinnovation.patterns.domain.Product;

public record ProductResponse(
    Long id,
    String name,
    BigDecimal price
) {
public static ProductResponse from(Product product) {
    return new ProductResponse(
            product.getProductId(),
            product.getName(),
            product.getPrice()
    );
}
}