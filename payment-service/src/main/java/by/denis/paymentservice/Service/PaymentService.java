package by.denis.paymentservice.Service;


import by.denis.paymentservice.dto.OrderCreatedEvent;

public interface PaymentService {
    void processPayment(OrderCreatedEvent event);
}
