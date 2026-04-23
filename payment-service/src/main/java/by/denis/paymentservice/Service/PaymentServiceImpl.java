package by.denis.paymentservice.Service;

import by.denis.paymentservice.PaymentEntity.PaymentEntity;
import by.denis.paymentservice.PaymentEntity.PaymentStatus;
import by.denis.paymentservice.Repository.PaymentRepository;
import by.denis.paymentservice.dto.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;
    private final KafkaTemplate<Spring,Object> kafkaTemplate;

    @Override
    @Transactional
    public void processPayment(OrderCreatedEvent event){
        log.info("Processing payment for order: {}", event.getOrderId());

        boolean success = simulatePayment(event);

        PaymentEntity payment = new PaymentEntity();

        payment.setOrderId(event.getOrderId());
        payment.setProductId(event.getProductId());
        payment.setQuantity(event.getQuantity());
        payment.setStatus(success ? PaymentStatus.CONFIRMED : PaymentStatus.FAILED);
        payment.setCreatedAt(LocalDateTime.now());

        repository.save(payment);

        String topic = success ? "payment-confirmed" : "payment-failed";
        if(success){
            kafkaTemplate.send(topic, Map.of(
                    "orderId", event.getOrderId(),
                    "productId", event.getProductId(),
                    "quantity", event.getQuantity()
            ));
        }else{
            kafkaTemplate.send(topic, Map.of("orderId", event.getOrderId()));
        }
        log.info("Sent {} event for order {}",topic,event.getOrderId());
    }
    private boolean simulatePayment(OrderCreatedEvent event){
        return true;
    }

}
