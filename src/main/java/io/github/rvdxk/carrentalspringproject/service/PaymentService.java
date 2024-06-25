package io.github.rvdxk.carrentalspringproject.service;

import io.github.rvdxk.carrentalspringproject.dto.PaymentDto;
import io.github.rvdxk.carrentalspringproject.entity.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> findAllPayments();
    void addPayment(Payment payment, Long id);
    void updatePayment(PaymentDto paymentDto, Long id);
    PaymentDto findPaymentById(Long id);
    void deletePayment(Long id);

}
