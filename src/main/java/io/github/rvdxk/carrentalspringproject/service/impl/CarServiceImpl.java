package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.dto.CarDto;
import io.github.rvdxk.carrentalspringproject.entity.Car;
import io.github.rvdxk.carrentalspringproject.entity.CarParam;
import io.github.rvdxk.carrentalspringproject.entity.Rental;
import io.github.rvdxk.carrentalspringproject.exception.ResourceNotFoundException;
import io.github.rvdxk.carrentalspringproject.mapper.CarMapper;
import io.github.rvdxk.carrentalspringproject.repository.CarParamRepository;
import io.github.rvdxk.carrentalspringproject.repository.CarRepository;
import io.github.rvdxk.carrentalspringproject.repository.RentalRepository;
import io.github.rvdxk.carrentalspringproject.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarParamRepository carParamRepository;
    @Autowired
    private RentalRepository rentalRepository;

    public CarServiceImpl(CarRepository carRepository, CarParamRepository carParamRepository, RentalRepository rentalRepository) {
        this.carRepository = carRepository;
        this.carParamRepository = carParamRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public List<CarDto> getAllCars() {
        List<Car> carList = carRepository.findAll();
        List<CarDto> carDtoList = carList.stream().
                map((car) -> CarMapper.mapToCarDto(car))
                .collect(Collectors.toUnmodifiableList());
        return carDtoList;
    }

    @Override
    public List<CarDto> getAvailableCars(LocalDate rentalDate, LocalDate returnDate) {
        List<Car> carList = carRepository.findAll();
        List<Rental> rentedRentals = rentalRepository.findByReturnDateIsNullOrReturnDateAfter(LocalDate.now());
        List<Car> rentedCars = rentedRentals.stream()
                .map(Rental::getCar)
                .collect(Collectors.toList());
        List<CarDto> rentedCarsDto = rentedCars.stream().
                map((car) -> CarMapper.mapToCarDto(car))
                .collect(Collectors.toUnmodifiableList());

        return rentedCarsDto;


    }

    @Override
    public void addCar(CarDto carDto) {
        Car car = CarMapper.mapToCar(carDto);
        carRepository.save(car);

    }

    @Override
    public CarDto getCarById(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car with id " + carId + " not found"));
        CarDto carDto = CarMapper.mapToCarDto(car);
        return carDto;
    }

    @Override
    public void updateCar(CarDto carDto, Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(()-> new ResourceNotFoundException("Car with id " + carId + " not found"));

        carRepository.save(CarMapper.mapToCar(carDto));
    }

    @Override
    public void updateCarParam(CarParam carParam, Long carId) {
        CarParam updateCarParam = carParamRepository.findById(carId)
                .orElseThrow(()-> new ResourceNotFoundException("Car with id " + carId + " not found"));

        carParamRepository.save(updateCarParam);
    }

    @Override
    public void deleteCar(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(()-> new ResourceNotFoundException("Car with id " + carId + " not found"));

        carRepository.deleteById(carId);
    }
}
