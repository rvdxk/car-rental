package io.github.rvdxk.carrentalspringproject.dto;

import io.github.rvdxk.carrentalspringproject.constant.Country;
import io.github.rvdxk.carrentalspringproject.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    private Long id;

    @NotEmpty(message = "Please, enter the first name.")
    private String firstName;

    @NotEmpty(message = "Please, enter the last name.")
    private String lastName;

    @NotEmpty(message = "Please, enter the ID card number.")
    private String idCardNumber;

    @NotEmpty(message = "Please, enter the correct phone number.")
    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please, select the date of birth.")
    private Date dateOfBirth;

    @NotEmpty(message = "Please, enter the street")

    private String street;
    private int houseNumber;
    private int apartmentNumber;
    private String city;
    private String postalCode;
    private Country country;

    private Long userId;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userRole;

}
