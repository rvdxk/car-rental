package io.github.rvdxk.carrentalspringproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car_parameters")
public class CarParams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @JoinColumn(name = "car_params_id", referencedColumnName = "id")
    private Long id;
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
    private String gearbox;
    @Column(nullable = false)
    private String driveWheels;


    @JsonIgnore
    @OneToOne(mappedBy = "carParams")
    private Car car;
}
