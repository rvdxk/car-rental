package io.github.rvdxk.carrentalspringproject.entity;

import io.github.rvdxk.carrentalspringproject.constant.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


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

    @OneToOne
    @JoinColumn(name = "rental_info_id")
    private RentalInfo rentalInfo;

    @Column(nullable = false)
    private Date paymentDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

}
