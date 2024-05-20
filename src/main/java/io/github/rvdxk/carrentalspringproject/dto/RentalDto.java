package io.github.rvdxk.carrentalspringproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class RentalDto {

    private Long id;
    private Long customerId;
    private Long carId;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private double totalCost;
}
