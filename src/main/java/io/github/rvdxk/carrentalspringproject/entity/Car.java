package io.github.rvdxk.carrentalspringproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Transient
    private Long carParamsId;

    @JsonIgnore
    @OneToOne(orphanRemoval = true)
    private CarParams carParams;

    @Transient
    private Long carLocationId;

    @JsonIgnore
    @OneToOne(orphanRemoval = true)
    private CarLocation carLocation;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false, unique = true)
    private String plateNumber;

    @Column(nullable = false)
    private int costPerDay;

    @Column(nullable = false)
    private boolean isAvailable;

}
