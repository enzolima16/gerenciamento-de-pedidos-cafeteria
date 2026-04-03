package one.digitalinnovation.patterns.service;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.patterns.domain.Customer;
import one.digitalinnovation.patterns.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
    }

    public Customer createCustomer(Customer customer) {
        validateCustomer(customer);
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existingCostumer = findCustomerById(id);

        existingCostumer.setName(updatedCustomer.getName());
        existingCostumer.setCpf(updatedCustomer.getCpf());
        validateCustomer(existingCostumer);
        return customerRepository.save(existingCostumer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = findCustomerById(id);
        customerRepository.delete(customer);
    }

    private void validateCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new RuntimeException("O nome é obrigatório");
        }

        if (customer.getCpf() == null || customer.getCpf().trim().isEmpty()) {
            throw new RuntimeException("CPF é obrigatório");
        }

        if (customer.getCpf().length() != 11) {
            throw new RuntimeException("CPF inválido");
        }
    }
}
