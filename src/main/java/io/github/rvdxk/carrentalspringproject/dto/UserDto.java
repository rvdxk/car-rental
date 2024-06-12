package io.github.rvdxk.carrentalspringproject.dto;

import io.github.rvdxk.carrentalspringproject.entity.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;

    @NotEmpty(message = "Please, enter the username.")
    private String username;

    @NotEmpty(message = "Please, enter the first name.")
    private String firstName;

    @NotEmpty(message = "Please, enter the last name.")
    private String lastName;

    @Email
    @NotEmpty(message = "Please, enter the email.")
    private String email;

    @NotEmpty(message = "Please, enter the password.")
    private String password;

    private Customer customer;


}
