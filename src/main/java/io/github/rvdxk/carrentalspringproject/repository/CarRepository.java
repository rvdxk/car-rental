package io.github.rvdxk.carrentalspringproject.repository;

import io.github.rvdxk.carrentalspringproject.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
