package by.denis.paymentservice.PaymentEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "order_id", nullable = false)
    private Long orderId;
    @Column(name = "product_id",nullable = false)
    private Long productId;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false,length = 20)
    private PaymentStatus status;
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;
}
