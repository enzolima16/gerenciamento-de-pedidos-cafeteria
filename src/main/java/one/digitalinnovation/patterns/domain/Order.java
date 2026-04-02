package one.digitalinnovation.patterns.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    //relação com o cliente
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    //lista de itens
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    //status do pedido
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //valor total
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    //data de criação
    @Column(nullable = false)
    private LocalDateTime createdAt;
}
