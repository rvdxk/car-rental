package io.github.rvdxk.carrentalspringproject.mapper;

import io.github.rvdxk.carrentalspringproject.dto.PaymentDto;
import io.github.rvdxk.carrentalspringproject.entity.Payment;

public class PaymentMapper {

    public static Payment mapToPayment(PaymentDto paymentDto){
        return Payment.builder()
                .id(paymentDto.getId())
                .totalCost(paymentDto.getTotalCost())
                .paymentDate(paymentDto.getPaymentDate())
                .paymentMethod(paymentDto.getPaymentMethod())
                .build();
    }

    public static PaymentDto mapToPaymentDto(Payment payment){
        return PaymentDto.builder()
                .id(payment.getId())
                .totalCost(payment.getTotalCost())
                .paymentDate(payment.getPaymentDate())
                .paymentMethod(payment.getPaymentMethod())
                .build();
    }
}
