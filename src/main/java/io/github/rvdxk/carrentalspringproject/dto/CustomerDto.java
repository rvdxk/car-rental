package io.github.rvdxk.carrentalspringproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDto {

    private Long id;
    @NotBlank(message = "Please, enter the first name.")
    private String firstName;
    @NotBlank(message = "Please, enter the last name.")
    private String lastName;
    @Email
    @NotBlank(message = "Please, enter the email address.")
    private String email;
    @NotBlank(message = "Please, enter the correct phone number.")
    private String phoneNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please, select the date of birth.")
    private Date dateOfBirth;
    @NotBlank(message = "Please, enter the street")
    private String street;
    @NotNull(message = "Please, enter the house number.")
    private Long houseNumber;
    private String apartmentNumber;
    @NotBlank(message = "Please, enter the city.")
    private String city;
    @NotBlank(message = "Please, enter the postal code.")
    private String postalCode;
    @NotBlank(message = "Please, enter the country.")
    private String country;

}
