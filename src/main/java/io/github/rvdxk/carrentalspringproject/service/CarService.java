package io.github.rvdxk.carrentalspringproject.service;

import io.github.rvdxk.carrentalspringproject.dto.CarDto;
import io.github.rvdxk.carrentalspringproject.entity.CarParams;
import io.github.rvdxk.carrentalspringproject.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface CarService {
    List<CarDto> getAllCars();
    List<CarDto> getRentedCars(LocalDate rentalDate, LocalDate returnDate);
    List<CarDto> getAvailableCars(LocalDate rentalDate, LocalDate returnDate);
    void addCar(CarDto carDto);
//    void addCarParams(Long id, CarParams carParams);
    CarDto getCarById(Long id);
    void updateCar(Long id, CarDto carDto);
    void updateCarParam(Long id, CarParams carParams);
    void deleteCar(Long id);
}
