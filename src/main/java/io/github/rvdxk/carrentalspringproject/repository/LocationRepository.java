package io.github.rvdxk.carrentalspringproject.repository;

import io.github.rvdxk.carrentalspringproject.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
