package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.constant.PaymentMethod;
import io.github.rvdxk.carrentalspringproject.constant.RentalStatus;
import io.github.rvdxk.carrentalspringproject.dto.RentalInfoDto;
import io.github.rvdxk.carrentalspringproject.entity.Payment;
import io.github.rvdxk.carrentalspringproject.entity.RentalInfo;
import io.github.rvdxk.carrentalspringproject.repository.RentalInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalInfoServiceImplTest {

    @Mock
    private RentalInfoRepository rentalInfoRepository;

    @InjectMocks
    private RentalInfoServiceImpl rentalInfoService;

    private RentalInfo rentalInfo1;
    private RentalInfo rentalInfo2;

    @BeforeEach
    void setUp() {

        Payment payment1 = Payment.builder()
                .id(1L)
                .totalCost(1200)
                .paymentDate(LocalDate.parse("2024-02-03"))
                .paymentMethod(PaymentMethod.CASH)
                .build();

        Payment payment2 = Payment.builder()
                .id(1L)
                .totalCost(1200)
                .paymentDate(LocalDate.parse("2024-02-03"))
                .paymentMethod(PaymentMethod.CASH)
                .build();

        rentalInfo1 = RentalInfo.builder()
                .id(1L)
                .carId(1L)
                .carLocationId(1L)
                .customerId(1L)
                .payment(payment1)
                .rentalDate(LocalDate.parse("2024-07-24"))
                .returnDate(LocalDate.parse("2024-07-31"))
                .build();



        rentalInfo2 = RentalInfo.builder()
                .id(2L)
                .carId(2L)
                .carLocationId(2L)
                .customerId(2L)
                .payment(payment2)
                .rentalDate(LocalDate.parse("2024-08-02"))
                .returnDate(LocalDate.parse("2024-08-09"))
                .build();


    }

    @Test
    void findAllRentalsInfo_ShouldReturnAllRentalsInfoWithIdInformation() {
        List<RentalInfo> rentalInfoList = Arrays.asList(rentalInfo1, rentalInfo2);

        when(rentalInfoRepository.findAll()).thenReturn(rentalInfoList);

        List<RentalInfoDto> result = rentalInfoService.findAllRentalsInfo();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(rentalInfoRepository, times(1)).findAll();

        assertEquals(rentalInfo1.getId(), result.get(0).getId());
        assertEquals(rentalInfo1.getCarId(), result.get(0).getCarId());
        assertEquals(rentalInfo1.getCarLocationId(), result.get(0).getCarLocationId());
        assertEquals(rentalInfo1.getPayment(), result.get(0).getPayment());

        assertEquals(rentalInfo2.getId(), result.get(1).getId());
        assertEquals(rentalInfo2.getCarId(), result.get(1).getCarId());
        assertEquals(rentalInfo2.getCarLocationId(), result.get(1).getCarLocationId());
        assertEquals(rentalInfo2.getPayment(), result.get(1).getPayment());

    }

    @Test
    void findRentalInfoById_ShouldReturnRentalInfoById() {
        when(rentalInfoRepository.findById(rentalInfo1.getId())).thenReturn(Optional.of(rentalInfo1));

        RentalInfoDto result = rentalInfoService.findRentalInfoById(rentalInfo1.getId());

        assertNotNull(result);

        verify(rentalInfoRepository, times(1)).findById(rentalInfo1.getId());

        assertEquals(rentalInfo1.getId(), result.getId());
        assertEquals(rentalInfo1.getCarId(), result.getCarId());
        assertEquals(rentalInfo1.getCarLocationId(), result.getCarLocationId());
        assertEquals(rentalInfo1.getPayment(), result.getPayment());
    }

    @Test
    void addRentalInfo_ShouldAddRentalInfoWithGeneralInformation() {
        when(rentalInfoRepository.save(rentalInfo1)).thenReturn(rentalInfo1);

        rentalInfoService.addRentalInfo(rentalInfo1);

        verify(rentalInfoRepository, times(1)).save(rentalInfo1);
    }

    @Test
    void updateRentalInfo_ShouldUpdateAllRentalInfoById() {
        RentalInfo updateRentalInfo = RentalInfo.builder()
                .id(2L)
                .carId(3L)
                .carLocationId(2L)
                .customerId(2L)
                .rentalDate(LocalDate.parse("2024-08-05"))
                .returnDate(LocalDate.parse("2024-08-11"))
                .build();

        when(rentalInfoRepository.findById(rentalInfo2.getId())).thenReturn(Optional.of(rentalInfo2));

        rentalInfoService.updateRentalInfo(rentalInfo2.getId(), updateRentalInfo);

        verify(rentalInfoRepository, times(1)).findById(rentalInfo2.getId());
        verify(rentalInfoRepository, times(1)).save(rentalInfo2);

        assertEquals(updateRentalInfo.getId(), rentalInfo2.getId());
        assertEquals(updateRentalInfo.getCarId(), rentalInfo2.getCarId());
        assertEquals(updateRentalInfo.getCarLocationId(), rentalInfo2.getCarLocationId());
        assertEquals(updateRentalInfo.getCustomerId(), rentalInfo2.getCustomerId());
    }

    @Test
    void deleteRentalInfo_ShouldDeleteRentalInfoById() {
        when(rentalInfoRepository.findById(rentalInfo1.getId())).thenReturn(Optional.of(rentalInfo1));

        rentalInfoService.deleteRentalInfo(rentalInfo1.getId());

        verify(rentalInfoRepository, times(1)).findById(rentalInfo1.getId());
        verify(rentalInfoRepository, times(1)).deleteById(rentalInfo1.getId());
    }

    @Test
    void addRentalStatus_ShouldAddRentalStatusByRentalAndReturnDate() {

        assertEquals(RentalStatus.AVAILABLE, rentalInfoService.addRentalStatus(null));

        RentalInfo rentalInfoWithNullDates = RentalInfo.builder()
                .id(1L)
                .carId(1L)
                .carLocationId(1L)
                .customerId(1L)
                .build();
        assertEquals(RentalStatus.AVAILABLE, rentalInfoService.addRentalStatus(rentalInfoWithNullDates));

        RentalInfo rentalInfoCompleted = RentalInfo.builder()
                .id(3L)
                .carId(3L)
                .carLocationId(3L)
                .customerId(3L)
                .rentalDate(LocalDate.parse("2024-02-12"))
                .returnDate(LocalDate.parse("2024-02-15"))
                .build();
        assertEquals(RentalStatus.COMPLETED, rentalInfoService.addRentalStatus(rentalInfoCompleted));

        RentalInfo rentalInfoInProgress = RentalInfo.builder()
                .id(4L)
                .carId(4L)
                .carLocationId(4L)
                .customerId(4L)
                .rentalDate(LocalDate.now().minusDays(1))
                .returnDate(LocalDate.now().plusDays(2))
                .build();
        assertEquals(RentalStatus.IN_PROGRESS, rentalInfoService.addRentalStatus(rentalInfoInProgress));

        RentalInfo rentalInfoUnknown = RentalInfo.builder()
                .id(5L)
                .carId(5L)
                .carLocationId(5L)
                .customerId(5L)
                .rentalDate(LocalDate.parse("2025-02-12"))
                .returnDate(LocalDate.parse("2024-02-12"))
                .build();
        assertEquals(RentalStatus.UNKNOWN, rentalInfoService.addRentalStatus(rentalInfoUnknown));
    }
}