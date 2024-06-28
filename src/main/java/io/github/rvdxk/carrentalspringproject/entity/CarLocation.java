package io.github.rvdxk.carrentalspringproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.rvdxk.carrentalspringproject.constant.Country;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "car_location")
public class CarLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "carLocation")
    private Car car;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private Long houseNumber;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String postalCode;

    @Enumerated(EnumType.STRING)
    private Country country;
}
