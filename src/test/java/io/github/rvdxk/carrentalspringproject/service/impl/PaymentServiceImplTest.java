package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.constant.PaymentMethod;
import io.github.rvdxk.carrentalspringproject.constant.PaymentStatus;
import io.github.rvdxk.carrentalspringproject.dto.PaymentDto;
import io.github.rvdxk.carrentalspringproject.entity.Payment;
import io.github.rvdxk.carrentalspringproject.entity.RentalInfo;
import io.github.rvdxk.carrentalspringproject.repository.PaymentRepository;
import io.github.rvdxk.carrentalspringproject.repository.RentalInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private RentalInfoRepository rentalInfoRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Payment payment1;
    private Payment payment2;

    private RentalInfo rentalInfo;


    @BeforeEach
    void setUp() throws ParseException {
        rentalInfo = RentalInfo.builder()
                .id(1L)
                .carId(1L)
                .carLocationId(1L)
                .rentalDate(LocalDate.parse("2024-02-03"))
                .returnDate(LocalDate.parse("2024-02-13"))
                .build();

        payment1 = Payment.builder()
                .id(1L)
                .totalCost(1200)
                .paymentDate(LocalDate.parse("2024-02-03"))
                .paymentMethod(PaymentMethod.CASH)
                .build();

        payment2 = Payment.builder()
                .id(2L)
                .totalCost(360)
                .paymentDate(LocalDate.parse("2024-07-30"))
                .paymentMethod(PaymentMethod.CARD)
                .build();
    }

        @Test
    void findAllPayments_ShouldReturnALlPaymentsWithGetRentalInfoId() {
            List<Payment> paymentList = Arrays.asList(payment1, payment2);

            when(paymentRepository.findAll()).thenReturn(paymentList);

            List<Payment> result = paymentService.findAllPayments();

            assertNotNull(result);
            assertEquals(2, result.size());

            verify(paymentRepository, times(1)).findAll();

            assertEquals(payment1.getTotalCost(), result.get(0).getTotalCost());
            assertEquals(payment1.getPaymentDate(), result.get(0).getPaymentDate());
            assertEquals(payment1.getPaymentMethod(), result.get(0).getPaymentMethod());
            assertEquals(payment1.getPaymentStatus(), result.get(0).getPaymentStatus());

            assertEquals(payment2.getTotalCost(), result.get(1).getTotalCost());
            assertEquals(payment2.getPaymentDate(), result.get(1).getPaymentDate());
            assertEquals(payment2.getPaymentMethod(), result.get(1).getPaymentMethod());
            assertEquals(payment2.getPaymentStatus(), result.get(1).getPaymentStatus());

    }

    @Test
    void addPayment_ShouldAddPaymentsToRentalInfoById() {
        when(rentalInfoRepository.findById(rentalInfo.getId())).thenReturn(Optional.of(rentalInfo));
        when(paymentRepository.save(payment1)).thenReturn(payment1);

        paymentService.addPayment(payment1, rentalInfo.getId());

        verify(paymentRepository, times(1)).save(payment1);
    }

    @Test
    void updatePayment_ShouldUpdatePaymentsToRentalInfoById() {
        PaymentDto updatePayment = PaymentDto.builder()
                .id(1L)
                .totalCost(1180)
                .paymentDate(LocalDate.parse("2024-02-04"))
                .paymentMethod(PaymentMethod.CASH)
                .build();

        when(rentalInfoRepository.findById(rentalInfo.getId())).thenReturn(Optional.of(rentalInfo));
        when(paymentRepository.findById(payment1.getId())).thenReturn(Optional.of(payment1));

        paymentService.updatePayment(updatePayment, rentalInfo.getId(), payment1.getId());

        verify(rentalInfoRepository, times(1)).findById(rentalInfo.getId());

        verify(paymentRepository, times(1)).findById(payment1.getId());
        verify(paymentRepository, times(1)).save(payment1);

        assertEquals(updatePayment.getTotalCost(), payment1.getTotalCost());
        assertEquals(updatePayment.getPaymentDate(), payment1.getPaymentDate());
        assertEquals(updatePayment.getPaymentMethod(), payment1.getPaymentMethod());
    }

    @Test
    void findPaymentById_ShouldGetPaymentByIdFromRentalInfo() {
        when(rentalInfoRepository.findById(rentalInfo.getId())).thenReturn(Optional.of(rentalInfo));
        when(paymentRepository.findById(payment1.getId())).thenReturn(Optional.of(payment1));

        PaymentDto result = paymentService.findPaymentById(rentalInfo.getId(), payment1.getId());

        assertNotNull(result);

        verify(rentalInfoRepository, times(1)).findById(rentalInfo.getId());
        verify(paymentRepository, times(1)).findById(payment1.getId());

        assertEquals(result.getTotalCost(), payment1.getTotalCost());
        assertEquals(result.getPaymentDate(), payment1.getPaymentDate());
        assertEquals(result.getPaymentMethod(), payment1.getPaymentMethod());
    }

    @Test
    void deletePayment_ShouldDeletePaymentFromRentalInfo() {
        when(rentalInfoRepository.findById(rentalInfo.getId())).thenReturn(Optional.of(rentalInfo));
        when(paymentRepository.findById(payment1.getId())).thenReturn(Optional.of(payment1));

        paymentService.deletePayment(rentalInfo.getId(), payment1.getId());

        verify(rentalInfoRepository, times(1)).findById(rentalInfo.getId());
        verify(paymentRepository, times(1)).findById(payment1.getId());
        verify(paymentRepository, times(1)).deleteById(payment1.getId());
    }

    @Test
    void testAddPaymentStatus_ShouldAddALlPaymentStatus() {

        Payment completedPayment = Payment.builder()
                .paymentDate(LocalDate.now())
                .totalCost(100.0)
                .build();
        assertEquals(PaymentStatus.COMPLETED, paymentService.addPaymentStatus(completedPayment));

        Payment pendingPayment = Payment.builder()
                .paymentDate(LocalDate.now().plusDays(1))
                .totalCost(100.0)
                .build();
        assertEquals(PaymentStatus.PENDING, paymentService.addPaymentStatus(pendingPayment));

        Payment cancelledPayment = Payment.builder()
                .paymentDate(LocalDate.now().minusDays(1))
                .totalCost(0.0)
                .build();
        assertEquals(PaymentStatus.CANCELLED, paymentService.addPaymentStatus(cancelledPayment));

        Payment invalidPayment = Payment.builder()
                .paymentDate(LocalDate.now())
                .totalCost(-20.0)
                .build();
        assertEquals(PaymentStatus.INVALID, paymentService.addPaymentStatus(invalidPayment));
    }
}