package one.digitalinnovation.patterns.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import one.digitalinnovation.patterns.domain.OrderStatus;
import one.digitalinnovation.patterns.dto.OrderItemRequest;
import one.digitalinnovation.patterns.dto.OrderResponse;
import one.digitalinnovation.patterns.service.OrderService;
import one.digitalinnovation.patterns.strategy.PaymentType;

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
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestParam Long customerId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(customerId));
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<OrderResponse> addItemToOrder(@PathVariable Long id,
                                                        @RequestBody @Valid OrderItemRequest request) {
        return ResponseEntity.ok(orderService.addItemToOrder(id, request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus(@PathVariable Long id,
                                                           @RequestParam OrderStatus newStatus) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, newStatus));
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<Void> pay(@PathVariable Long id, 
        @RequestParam PaymentType type){
        orderService.processPayment(id, type);
        return ResponseEntity.ok().build();
    }
}