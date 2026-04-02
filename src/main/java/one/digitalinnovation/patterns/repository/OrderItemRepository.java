package one.digitalinnovation.patterns.repository;

import one.digitalinnovation.patterns.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}