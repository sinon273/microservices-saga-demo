package by.denis.paymentservice.kafka;

import by.denis.paymentservice.Service.PaymentService;
import by.denis.paymentservice.dto.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentEventConsumer {
    private final PaymentService paymentService;

    @KafkaListener(topics = "order-created",groupId = "payment-group")
    public void consumerOrderCreated(OrderCreatedEvent event){
        log.info("Received order-created event: {}", event);
        paymentService.processPayment(event);
    }
}
