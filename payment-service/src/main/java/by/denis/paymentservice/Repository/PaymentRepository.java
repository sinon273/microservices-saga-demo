package by.denis.paymentservice.Repository;

import by.denis.paymentservice.PaymentEntity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {
}
