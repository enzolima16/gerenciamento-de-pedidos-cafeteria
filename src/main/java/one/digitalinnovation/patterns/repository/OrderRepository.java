package one.digitalinnovation.patterns.repository;

import one.digitalinnovation.patterns.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}