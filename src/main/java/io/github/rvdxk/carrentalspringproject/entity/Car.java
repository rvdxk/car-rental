package io.github.rvdxk.carrentalspringproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "car")
public class Car {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false, unique = true)
    private String plateNumber;
    @Column(nullable = false)
    private int pricePerHour;
    @Column(nullable = false)
    private boolean isAvailable;
}
