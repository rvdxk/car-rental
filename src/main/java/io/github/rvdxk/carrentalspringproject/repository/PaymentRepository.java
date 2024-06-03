package io.github.rvdxk.carrentalspringproject.repository;

import io.github.rvdxk.carrentalspringproject.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
