package io.github.rvdxk.carrentalspringproject.service;

import io.github.rvdxk.carrentalspringproject.dto.CarDto;
import io.github.rvdxk.carrentalspringproject.entity.Car;
import io.github.rvdxk.carrentalspringproject.entity.CarParams;
import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    void addCar(CarDto carDto);
    void addCarParams(Long id, CarParams carParams);
    CarDto getCarById(Long id);
    void updateCar(Long id, CarDto carDto);
    void updateCarParam(Long id, CarParams carParams);
    void deleteCar(Long id);
}
