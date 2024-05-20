package io.github.rvdxk.carrentalspringproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "car")
public class Car {
    private String brand;
    private String model;
    private String plateNumber;
    private Long pricePerHour;
    private boolean isAvailable;
}
