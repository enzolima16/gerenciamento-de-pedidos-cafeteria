package one.digitalinnovation.patterns.repository;

import one.digitalinnovation.patterns.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}