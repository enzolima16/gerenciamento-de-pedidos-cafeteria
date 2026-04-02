package one.digitalinnovation.patterns.repository;

import one.digitalinnovation.patterns.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}