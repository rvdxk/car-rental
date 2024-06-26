package io.github.rvdxk.carrentalspringproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.rvdxk.carrentalspringproject.constant.PaymentMethod;
import io.github.rvdxk.carrentalspringproject.constant.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rental_info_id", nullable = false)
    @JsonIgnore
    private RentalInfo rentalInfo;

    @Column(nullable = false)
    private double totalCost;

    @Column(nullable = false)
    private LocalDate paymentDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;


}
