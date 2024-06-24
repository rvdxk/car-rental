package io.github.rvdxk.carrentalspringproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarRentalSpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalSpringProjectApplication.class, args);
	}

}

//feedback po edicie java.lang.NullPointerException: Cannot invoke
// "io.github.rvdxk.carrentalspringproject.entity.User.getEmail()"
// because the return value of "io.github.rvdxk.carrentalspringproject.entity.Feedback.getUser()" is null
