package io.github.rvdxk.carrentalspringproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.rvdxk.carrentalspringproject.constant.Gearbox;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "car_parameters")
public class CarParams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "carParams")
    private Car car;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int prodYear;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private int numberOfDoors;

    @Column(nullable = false)
    private int numberOfSeats;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gearbox gearbox;

    @Column(nullable = false)
    private String driveWheels;
}
