package io.github.rvdxk.carrentalspringproject.dto;

import io.github.rvdxk.carrentalspringproject.entity.CarParams;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CarDto {

    private Long id;
    private String make;
    private String model;
    private String plateNumber;
    private int costPerHour;
    private boolean isAvailable;
    private CarParams carParams;
}
