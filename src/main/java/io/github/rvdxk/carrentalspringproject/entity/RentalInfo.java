package io.github.rvdxk.carrentalspringproject.entity;

import io.github.rvdxk.carrentalspringproject.constant.RentalStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "rental_information")
public class RentalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @OneToOne(mappedBy = "rentalInfo", cascade = CascadeType.ALL)
    private Payment payment;

    @Column(nullable = false)
    private LocalDate rentalDate;

    @Column(nullable = false)
    private LocalDate returnDate;

    @Column(nullable = false)
    private double totalCost;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;

}
