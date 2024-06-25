package io.github.rvdxk.carrentalspringproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.rvdxk.carrentalspringproject.constant.PaymentStatus;
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
    @Column(unique = true, nullable = false)
    private Long id;

    @JoinColumn(name = "customer_id", nullable = false)
    private Long customerId;

    @JoinColumn(name = "car_id", nullable = false)
    private Long carId;

    @JoinColumn(name = "car_location_id", nullable = false)
    private Long carLocationId;

    @OneToOne(mappedBy = "rentalInfo", cascade = CascadeType.ALL)
    private Payment payment;

    @Column(nullable = false)
    private LocalDate rentalDate;

    @Column(nullable = false)
    private LocalDate returnDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;

}
