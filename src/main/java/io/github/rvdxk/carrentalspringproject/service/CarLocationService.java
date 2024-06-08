package io.github.rvdxk.carrentalspringproject.service;

import io.github.rvdxk.carrentalspringproject.entity.CarLocation;

import java.util.List;

public interface CarLocationService {

    List<CarLocation> getAllCarLocation();
    CarLocation getCarLocationById(Long id);
    void addCarLocation(CarLocation carLocation);
    void updateCarLocation(CarLocation carLocation, Long id);
    void deleteCarLocation(Long id);
}
