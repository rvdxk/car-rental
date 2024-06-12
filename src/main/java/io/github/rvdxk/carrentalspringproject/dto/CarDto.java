package io.github.rvdxk.carrentalspringproject.dto;

import io.github.rvdxk.carrentalspringproject.entity.CarLocation;
import io.github.rvdxk.carrentalspringproject.entity.CarParams;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CarDto {

    private Long id;

    @NotBlank(message = "Please, enter the make of the car.")
    private String make;

    @NotBlank(message = "Please, enter the model of the car.")
    private String model;

    @NotBlank(message = "Please, enter the plate number of the car.")
    private String plateNumber;

    @Min(value = 0, message = "Cost per day must be greater than or equal to zero.")
    private int costPerDay;

    private boolean isAvailable;

    private CarParams carParams;

    private CarLocation carLocation;
}
