package one.digitalinnovation.patterns.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderItemRequest(

    @NotNull(message = "O ID do produto é obrigatório")
    Long productId,

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser maior que zero")
    Integer quantity
) {}