package io.github.rvdxk.carrentalspringproject.mapper;

import io.github.rvdxk.carrentalspringproject.dto.RentalInfoDto;
import io.github.rvdxk.carrentalspringproject.entity.RentalInfo;

public class RentalInfoMapper {

    public static RentalInfo mapToRentalInfo(RentalInfoDto rentalInfoDto){
        return RentalInfo.builder()
                .id(rentalInfoDto.getId())
                .customerId(rentalInfoDto.getCustomerId())
                .carId(rentalInfoDto.getCarId())
                .carLocationId(rentalInfoDto.getCarLocationId())
                .payment(rentalInfoDto.getPayment())
                .rentalDate(rentalInfoDto.getRentalDate())
                .returnDate(rentalInfoDto.getReturnDate())

                .build();
    }

    public static RentalInfoDto mapToRentalInfoDto(RentalInfo rentalInfo){
        return RentalInfoDto.builder()
                .id(rentalInfo.getId())
                .customerId(rentalInfo.getCustomerId())
                .carId(rentalInfo.getCarId())
                .carLocationId(rentalInfo.getCarLocationId())
                .payment(rentalInfo.getPayment())
                .rentalDate(rentalInfo.getRentalDate())
                .returnDate(rentalInfo.getReturnDate())
                .build();
    }
}
