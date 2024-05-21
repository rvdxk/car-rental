package io.github.rvdxk.carrentalspringproject.entity;

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
//one to one
public class CarParam {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long carParamId;
    @Column(nullable = false, unique = true)
    private String plateNumber;
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

//    @OneToOne(mappedBy = "carParameters")
//    private Car car;

}
