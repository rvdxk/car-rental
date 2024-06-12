package io.github.rvdxk.carrentalspringproject.service;

import io.github.rvdxk.carrentalspringproject.dto.CarDto;
import io.github.rvdxk.carrentalspringproject.entity.Car;
import io.github.rvdxk.carrentalspringproject.entity.CarLocation;
import io.github.rvdxk.carrentalspringproject.entity.CarParams;
import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    void addCar(CarDto carDto);
    void addCarParams(Long id, CarParams carParams);
    void addCarLocation(Long id, CarLocation carLocation);
    CarDto getCarById(Long id);
    CarLocation getCarLocationById(Long id);
    void updateCar(Long id, CarDto carDto);
    void updateCarParam(Long id, CarParams carParams);
    void updateCarLocation(CarLocation carLocation, Long id);
    void deleteCar(Long id);
    void deleteCarLocation(Long id);
}
