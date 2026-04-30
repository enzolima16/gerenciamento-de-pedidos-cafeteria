package one.digitalinnovation.patterns.controller;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.patterns.domain.Order;
import one.digitalinnovation.patterns.domain.OrderStatus;
import one.digitalinnovation.patterns.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById (@PathVariable Long id){
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Order> createProduct (@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(id));
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<Order> addItemToOrder(@PathVariable Long id,
                                                @RequestParam Long productId,
                                                @RequestParam Integer quantity) {
        Order order = orderService.addItemToOrder(id,  productId, quantity);
        return ResponseEntity.ok(order);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id,
                                                   @RequestParam OrderStatus newStatus) {
        Order order = orderService.updateOrderStatus(id, newStatus);
        return ResponseEntity.ok(order);
    }
}