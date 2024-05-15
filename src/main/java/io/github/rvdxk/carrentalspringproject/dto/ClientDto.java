package io.github.rvdxk.carrentalspringproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private Long id;
    @NotEmpty(message = "Please, enter a first name.")
    private String firstName;
    @NotEmpty(message = "Please, enter a last name.")
    private String lastName;
    @Email
    @NotEmpty(message = "Please, enter an email address. ")
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please, select a date of birth.")
    private Date dateOfBirth;
    @NotEmpty(message = "Please, enter an address.")
    private String address;

}
