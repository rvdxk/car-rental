package io.github.rvdxk.carrentalspringproject.service;

import io.github.rvdxk.carrentalspringproject.dto.CarDto;
import io.github.rvdxk.carrentalspringproject.entity.CarParam;

import java.time.LocalDate;
import java.util.List;

public interface CarService {
    List<CarDto> getAllCars();
    List<CarDto> getAvailableCars(LocalDate rentalDate, LocalDate returnDate);
    void addCar(CarDto carDto);
    CarDto getCarById(Long carId);
    void updateCar(CarDto carDto, Long carId);
    void updateCarParam(CarParam carParam, Long carParamId);
    void deleteCar(Long carId);
}
