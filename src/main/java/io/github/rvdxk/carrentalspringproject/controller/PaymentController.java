package io.github.rvdxk.carrentalspringproject.controller;

import io.github.rvdxk.carrentalspringproject.dto.PaymentDto;
import io.github.rvdxk.carrentalspringproject.entity.Payment;
import io.github.rvdxk.carrentalspringproject.service.PaymentService;
import io.github.rvdxk.carrentalspringproject.service.RentalInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/rental-info/payments/all")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.findAllPayments();
        return ResponseEntity.ok(payments);
    }

    @PostMapping("/rental-info/{rentalInfoId}/payments")
    public ResponseEntity<String> addPayment(@PathVariable("rentalInfoId") Long rentalInfoId,
                                             @RequestBody @Valid Payment payment) {
        paymentService.addPayment(payment, rentalInfoId);
        return new ResponseEntity<>("Payment successfully added!",HttpStatus.CREATED);
    }

    @PutMapping("/rental-info/{rentalInfoId}/payments/{paymentId}")
    public ResponseEntity<String> updatePayment(@PathVariable("rentalInfoId") Long rentalInfoId,
                                                @PathVariable("paymentId") Long paymentId,
                                                @RequestBody @Valid PaymentDto paymentDto){

        paymentService.updatePayment(paymentDto,rentalInfoId, paymentId);

        return ResponseEntity.ok("Payment successfully updated!");
    }

    @GetMapping("/rental-info/{rentalInfoId}/payments/{paymentId}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable("rentalInfoId") Long rentalInfoId,
                                                     @PathVariable("paymentId") Long paymentId) {
        PaymentDto paymentDto = paymentService.findPaymentById(rentalInfoId, paymentId);

        return ResponseEntity.ok(paymentDto);
    }

    @DeleteMapping("/rental-info/{rentalInfoId}/payments/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable("rentalInfoId") Long rentalInfoId,
                                                @PathVariable("paymentId") Long paymentId) {
        paymentService.deletePayment(rentalInfoId, paymentId);

        return ResponseEntity.ok("Payment successfully deleted!");
    }
}