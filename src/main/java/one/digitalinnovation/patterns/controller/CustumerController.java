package one.digitalinnovation.patterns.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import one.digitalinnovation.patterns.domain.Customer;
import one.digitalinnovation.patterns.service.CustomerService;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustumerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers () {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById (@PathVariable Long id){
        return ResponseEntity.ok(customerService.findCustomerById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Customer> createCostumer (@RequestBody Customer costumer) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.createCustomer(costumer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCostumer (@PathVariable Long id, @RequestBody Customer updatedCustomer){
        return ResponseEntity.ok(customerService.updateCustomer(id, updatedCustomer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer (@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}