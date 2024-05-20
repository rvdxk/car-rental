package io.github.rvdxk.carrentalspringproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "car_parameters")
//one to one
public class CarParam {
    @Column(nullable = false, unique = true)
    private String plateNumber;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private int doorsQuantity;
    @Column(nullable = false)
    private int seatsQuantity;
    @Column(nullable = false)
    private String gearbox;
    @Column(nullable = false)
    private String driveWheels;

}
