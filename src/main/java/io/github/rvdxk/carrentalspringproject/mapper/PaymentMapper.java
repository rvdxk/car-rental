package io.github.rvdxk.carrentalspringproject.mapper;

import io.github.rvdxk.carrentalspringproject.dto.PaymentDto;
import io.github.rvdxk.carrentalspringproject.entity.Payment;

public class PaymentMapper {

    public static Payment mapToPayment(PaymentDto paymentDto){
        return Payment.builder()
                .id(paymentDto.getId())
                .rentalInfo(paymentDto.getRentalInfo())
                .paymentDate(paymentDto.getPaymentDate())
                .paymentMethod(paymentDto.getPaymentMethod())
                .build();
    }

    public static PaymentDto mapToPaymentDto(Payment payment){
        return PaymentDto.builder()
                .id(payment.getId())
                .rentalInfo(payment.getRentalInfo())
                .paymentDate(payment.getPaymentDate())
                .paymentMethod(payment.getPaymentMethod())
                .build();
    }
}
