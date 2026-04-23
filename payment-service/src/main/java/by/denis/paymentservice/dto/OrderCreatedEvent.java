package by.denis.paymentservice.dto;

import lombok.Data;

@Data
public class OrderCreatedEvent {
    private Long orderId;
    private Long productId;
    private int quantity;
}
