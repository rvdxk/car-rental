package io.github.rvdxk.carrentalspringproject.dto;

import io.github.rvdxk.carrentalspringproject.entity.Car;
import io.github.rvdxk.carrentalspringproject.entity.Customer;
import io.github.rvdxk.carrentalspringproject.entity.Location;
import io.github.rvdxk.carrentalspringproject.entity.Payment;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RentalInfoDto {

    private Long id;

    @NotNull(message = "Please, enter the customer ID.")
    private Customer customer;

    @NotNull(message = "Please, enter the car ID.")
    private Car car;

    @NotNull(message = "Please, enter the location ID.")
    private Location location;

    @NotNull(message = "Please, enter the payment information.")
    private Payment payment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please, select the rental date.")
    private LocalDate rentalDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please, select the return date.")
    private LocalDate returnDate;

    @NotNull(message = "Please, enter total cost.")
    private double totalCost;

}