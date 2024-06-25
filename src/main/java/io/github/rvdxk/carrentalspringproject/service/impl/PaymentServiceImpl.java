package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.constant.PaymentStatus;
import io.github.rvdxk.carrentalspringproject.constant.RentalStatus;
import io.github.rvdxk.carrentalspringproject.dto.PaymentDto;
import io.github.rvdxk.carrentalspringproject.entity.Payment;
import io.github.rvdxk.carrentalspringproject.entity.RentalInfo;
import io.github.rvdxk.carrentalspringproject.exception.ResourceNotFoundException;
import io.github.rvdxk.carrentalspringproject.mapper.PaymentMapper;
import io.github.rvdxk.carrentalspringproject.repository.PaymentRepository;
import io.github.rvdxk.carrentalspringproject.repository.RentalInfoRepository;
import io.github.rvdxk.carrentalspringproject.service.PaymentService;
import io.github.rvdxk.carrentalspringproject.service.RentalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final RentalInfoRepository rentalInfoRepository;

    @Override
    public List<Payment> findAllPayments() {
        List<Payment>  findAllPayments = paymentRepository.findAll();
        return findAllPayments;
    }

    @Override
    public void addPayment(Payment payment,
                           Long id) {
        RentalInfo rentalInfo = rentalInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental information with id " + id + " not found"));

        if (rentalInfo.getPayment() != null){
            paymentRepository.delete(rentalInfo.getPayment());
        }

        payment.setRentalInfo(rentalInfo);
        payment.setPaymentStatus(addPaymentStatus(payment));
        paymentRepository.save(payment);

        rentalInfo.setPayment(payment);
        rentalInfoRepository.save(rentalInfo);
    }

    @Override
    public void updatePayment(PaymentDto paymentDto, Long id) {
        RentalInfo rentalInfo = rentalInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental information with id " + id + " not found"));

        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Payment with id " + id + " not found"));

        existingPayment.setTotalCost(paymentDto.getTotalCost());
        existingPayment.setPaymentDate(paymentDto.getPaymentDate());
        existingPayment.setPaymentMethod(paymentDto.getPaymentMethod());
        existingPayment.setPaymentStatus(addPaymentStatus(PaymentMapper.mapToPayment(paymentDto)));

        paymentRepository.save(existingPayment);
        rentalInfoRepository.save(rentalInfo);
    }

    @Override
    public PaymentDto findPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Payment with id " + id + " not found"));
        PaymentDto paymentDto = PaymentMapper.mapToPaymentDto(payment);
        return paymentDto;
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Payment with id " + id + " not found"));

        paymentRepository.deleteById(id);

    }

    public PaymentStatus addPaymentStatus(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("The payment status is not initialized yet.");
        }

        LocalDate now = LocalDate.now();
        LocalDate paymentDate = payment.getPaymentDate();
        double totalCost = payment.getTotalCost();

        if (paymentDate.isBefore(now) || paymentDate.isEqual(now)) {
            return PaymentStatus.COMPLETED;
        }
        if (paymentDate.isAfter(now)) {
            return PaymentStatus.PENDING;
        }
        if (totalCost == 0) {
            return PaymentStatus.CANCELLED;
        } else {
            return PaymentStatus.FAILED;
        }
    }
}
