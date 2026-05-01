package one.digitalinnovation.patterns.service;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.patterns.domain.Customer;
import one.digitalinnovation.patterns.dto.CustomerRequest;
import one.digitalinnovation.patterns.dto.CustomerResponse;
import one.digitalinnovation.patterns.exception.ResourceNotFoundException;
import one.digitalinnovation.patterns.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerResponse::from)
                .toList();
    }

    public CustomerResponse findCustomerById(Long id) {
        return CustomerResponse.from(getCustomerOrThrow(id));
    }

    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setCpf(request.cpf());
        return CustomerResponse.from(customerRepository.save(customer));
    }

    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        Customer customer = getCustomerOrThrow(id);
        customer.setName(request.name());
        customer.setCpf(request.cpf());
        return CustomerResponse.from(customerRepository.save(customer));
    }

    public void deleteCustomer(Long id) {
        customerRepository.delete(getCustomerOrThrow(id));
    }

    public Customer getCustomerOrThrow(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }
}