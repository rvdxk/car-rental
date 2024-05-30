package io.github.rvdxk.carrentalspringproject.repository;

import io.github.rvdxk.carrentalspringproject.entity.RentalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalInfoRepository extends JpaRepository<RentalInfo, Long> {
}
