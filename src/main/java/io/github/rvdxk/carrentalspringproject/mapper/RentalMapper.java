package io.github.rvdxk.carrentalspringproject.mapper;

import io.github.rvdxk.carrentalspringproject.dto.RentalDto;
import io.github.rvdxk.carrentalspringproject.entity.Rental;

public class RentalMapper {

    public static RentalDto mapToRentalDto(Rental rental) {
        RentalDto rentalDto = new RentalDto(
                rental.getId(),
                rental.getCustomerId(),
                rental.getCarId(),
                rental.getRentalDate(),
                rental.getReturnDate(),
                rental.getTotalCost()
        );
        return rentalDto;
    }

    public static Rental mapToRental(RentalDto rentalDto) {
        Rental rental = new Rental(
                rentalDto.getId(),
                rentalDto.getCustomerId(),
                rentalDto.getCarId(),
                rentalDto.getRentalDate(),
                rentalDto.getReturnDate(),
                rentalDto.getTotalCost()
        );
        return rental;
    }
}
