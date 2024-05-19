package io.github.rvdxk.carrentalspringproject.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private Long id;
    @NotEmpty(message = "Please, enter the first name.")
    private String firstName;
    @NotEmpty(message = "Please, enter the last name.")
    private String lastName;
    @Email
    @NotEmpty(message = "Please, enter the email address.")
    private String email;
    @NotEmpty(message = "Please, enter the correct phone number.")
    private String phoneNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please, select the date of birth.")
    private Date dateOfBirth;
    @NotEmpty(message = "Please, enter the street")
    private String street;
    @NotNull(message = "Please, enter the house number.")
    private Long houseNumber;
    private String localNumber;
    @NotEmpty(message = "Please, enter the city.")
    private String city;
    @NotEmpty(message = "Please, enter the postal code.")
    private String postalCode;
    @NotEmpty(message = "Please, enter the country.")
    private String country;

}
