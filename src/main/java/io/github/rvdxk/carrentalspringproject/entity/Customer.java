package io.github.rvdxk.carrentalspringproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.rvdxk.carrentalspringproject.constant.Country;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "customer")
    private User user;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String idCardNumber;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String street;
    private Long houseNumber;
    private String apartmentNumber;
    private String city;
    private String postalCode;

    @Enumerated(EnumType.STRING)
    private Country country;


}
