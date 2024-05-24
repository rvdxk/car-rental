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
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @PrimaryKeyJoinColumn(name = "id")
    private Long id;
    @Column(nullable = false)
    private String make;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false, unique = true)
    private String plateNumber;
    @Column(nullable = false)
    private int costPerHour;
    @Column(nullable = false)
    private boolean isAvailable;

    @JsonIgnore
    @OneToOne(orphanRemoval = true)
    private CarParams carParams;
}
