package io.github.rvdxk.carrentalspringproject.repository;

import io.github.rvdxk.carrentalspringproject.entity.CarLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarLocationRepository extends JpaRepository<CarLocation, Long> {
}
