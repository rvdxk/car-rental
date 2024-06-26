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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public void addPayment(Payment payment, Long rentalInfoId) {

        RentalInfo rentalInfo = rentalInfoRepository.findById(rentalInfoId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental information with id " + rentalInfoId + " not found"));

        if (rentalInfo.getPayment() != null){
            paymentRepository.delete(rentalInfo.getPayment());
        }

        payment.setRentalInfo(rentalInfo);
        payment.setPaymentStatus(addPaymentStatus(payment));
        paymentRepository.save(payment);

        rentalInfo.setPayment(payment);
        rentalInfoRepository.save(rentalInfo);
    }

    @Transactional
    @Override
    public void updatePayment(PaymentDto paymentDto, Long rentalInfoId, Long paymentId) {
        RentalInfo rentalInfo = rentalInfoRepository.findById(rentalInfoId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental information with id " + rentalInfoId + " not found"));

        Payment existingPayment = paymentRepository.findById(paymentId)
                .orElseThrow(()-> new ResourceNotFoundException("Payment with id " + paymentId + " not found"));

        existingPayment.setTotalCost(paymentDto.getTotalCost());
        existingPayment.setPaymentDate(paymentDto.getPaymentDate());
        existingPayment.setPaymentMethod(paymentDto.getPaymentMethod());
        existingPayment.setPaymentStatus(addPaymentStatus(PaymentMapper.mapToPayment(paymentDto)));

        paymentRepository.save(existingPayment);
        rentalInfoRepository.save(rentalInfo);
    }

    @Override
    public PaymentDto findPaymentById(Long rentalInfoId, Long paymentId) {
        RentalInfo rentalInfo = rentalInfoRepository.findById(rentalInfoId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental information with id " + rentalInfoId + " not found"));

        Payment existingPayment = paymentRepository.findById(paymentId)
                .orElseThrow(()-> new ResourceNotFoundException("Payment with id " + paymentId + " not found"));

        PaymentDto paymentDto = PaymentMapper.mapToPaymentDto(existingPayment);

        return paymentDto;
    }

    @Transactional
    @Override
    public void deletePayment(Long rentalInfoId, Long paymentId) {
        RentalInfo rentalInfo = rentalInfoRepository.findById(rentalInfoId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental information with id " + rentalInfoId + " not found"));

        paymentRepository.findById(paymentId)
                .orElseThrow(()-> new ResourceNotFoundException("Payment with id " + paymentId + " not found"));

        rentalInfo.setPayment(null);
        rentalInfoRepository.save(rentalInfo);

        paymentRepository.deleteById(paymentId);

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
