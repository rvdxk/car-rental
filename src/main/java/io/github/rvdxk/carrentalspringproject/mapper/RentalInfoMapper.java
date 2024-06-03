package io.github.rvdxk.carrentalspringproject.mapper;

import io.github.rvdxk.carrentalspringproject.dto.RentalInfoDto;
import io.github.rvdxk.carrentalspringproject.entity.RentalInfo;

public class RentalInfoMapper {

    public static RentalInfo mapToRentalInfo(RentalInfoDto rentalInfoDto){
        return RentalInfo.builder()
                .id(rentalInfoDto.getId())
                .customer(rentalInfoDto.getCustomer())
                .car(rentalInfoDto.getCar())
                .location(rentalInfoDto.getLocation())
                .payment(rentalInfoDto.getPayment())
                .rentalDate(rentalInfoDto.getRentalDate())
                .returnDate(rentalInfoDto.getReturnDate())
                .totalCost(rentalInfoDto.getTotalCost())
                .build();
    }

    public static RentalInfoDto mapToRentalInfoDto(RentalInfo rentalInfo){
        return RentalInfoDto.builder()
                .id(rentalInfo.getId())
                .customer(rentalInfo.getCustomer())
                .car(rentalInfo.getCar())
                .location(rentalInfo.getLocation())
                .payment(rentalInfo.getPayment())
                .rentalDate(rentalInfo.getRentalDate())
                .returnDate(rentalInfo.getReturnDate())
                .totalCost(rentalInfo.getTotalCost())
                .build();
    }
}
