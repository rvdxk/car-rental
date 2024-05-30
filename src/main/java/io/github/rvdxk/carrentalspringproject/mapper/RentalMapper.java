package io.github.rvdxk.carrentalspringproject.mapper;

import io.github.rvdxk.carrentalspringproject.dto.RentalDto;
import io.github.rvdxk.carrentalspringproject.entity.Rental;

public class RentalMapper {

    public static RentalDto mapToRentalDto(Rental rental) {
        RentalDto rentalDto = new RentalDto(
                rental.getId(),
                rental.getRentalStatus(),
                rental.getCustomer(),
                rental.getCar(),
                rental.getRentalInfo()
        );
        return rentalDto;
    }

    public static Rental mapToRental(RentalDto rentalDto) {
        Rental rental = new Rental(
                rentalDto.getId(),
                rentalDto.getRentalStatus(),
                rentalDto.getCustomer(),
                rentalDto.getCar(),
                rentalDto.getRentalInfo()
        );
        return rental;
    }
}
