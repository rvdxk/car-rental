package io.github.rvdxk.carrentalspringproject.repository;

import io.github.rvdxk.carrentalspringproject.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}

