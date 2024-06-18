package io.github.rvdxk.carrentalspringproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarRentalSpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalSpringProjectApplication.class, args);
	}

}


//zmienic get na find
//w customer get nie ma user id
// customer poza add dostep tylko dla admina
//caly car controller nie dziala - 403
//refresh token