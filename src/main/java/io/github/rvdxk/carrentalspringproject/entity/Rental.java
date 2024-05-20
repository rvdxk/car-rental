package io.github.rvdxk.carrentalspringproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rental_information")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long customerId;
    @Column(nullable = false)
    private Long carId;
    @Column(nullable = false)
    private LocalDate rentalDate;
    @Column(nullable = false)
    private LocalDate returnDate;
    @Column(nullable = false)
    private double totalCost;
}
