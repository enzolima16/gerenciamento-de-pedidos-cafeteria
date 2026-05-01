package one.digitalinnovation.patterns.dto;

import one.digitalinnovation.patterns.domain.Customer;

public record CustomerResponse(
    Long id,
    String name,
    String cpf
) {
public static CustomerResponse from(Customer customer) {
    return new CustomerResponse(
            customer.getCustomerId(),
            customer.getName(),
            customer.getCpf()
    );
}
}