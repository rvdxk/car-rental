package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.dto.CarDto;
import io.github.rvdxk.carrentalspringproject.entity.Car;
import io.github.rvdxk.carrentalspringproject.entity.CarParams;
import io.github.rvdxk.carrentalspringproject.entity.Rental;
import io.github.rvdxk.carrentalspringproject.exception.ResourceNotFoundException;
import io.github.rvdxk.carrentalspringproject.mapper.CarMapper;
import io.github.rvdxk.carrentalspringproject.repository.CarParamsRepository;
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
    private CarParamsRepository carParamsRepository;
    @Autowired
    private RentalRepository rentalRepository;

    public CarServiceImpl(CarRepository carRepository, CarParamsRepository carParamsRepository, RentalRepository rentalRepository) {
        this.carRepository = carRepository;
        this.carParamsRepository = carParamsRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public List<CarDto> getAllCars() {
        List<Car> carsList = carRepository.findAll();
        List<CarDto> carsDtoList = carsList.stream().
                map((car) -> CarMapper.mapToCarDto(car))
                .toList();
        return carsDtoList;
    }

    @Override
    public List<CarDto> getRentedCars(LocalDate rentalDate, LocalDate returnDate) {
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
    public List<CarDto> getAvailableCars(LocalDate rentalDate, LocalDate returnDate) {
        List<Car> allCars = carRepository.findAll();
        List<Rental> rentedCars = rentalRepository.findByReturnDateIsNullAndRentalDateBetween(rentalDate, returnDate);

        List<Car> rentedCarEntities = rentedCars.stream()
                .map(Rental::getCar)
                .collect(Collectors.toList());
        List<CarDto> availableCars = allCars.stream()
                .map(car -> {
                    boolean isAvailable = !false;
                    car.setAvailable(isAvailable);
                    return CarMapper.mapToCarDto(car);
                })
                .filter(carDto -> !carDto.isAvailable())
                .collect(Collectors.toList());

        if (availableCars.isEmpty()) {
            throw new ResourceNotFoundException("Sorry, we don't have any available cars for the specified dates.");
        }

        return availableCars;
    }

    @Override
    public CarDto getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car with id " + id + " not found"));
        CarDto carDto = CarMapper.mapToCarDto(car);
        return carDto;
    }

    @Override
    public void addCar(CarDto carDto) {
        Car car = CarMapper.mapToCar(carDto);
        carRepository.save(car);
    }

//    @Override
//    public void addCarParams(Long id, CarParams carParams) {
//        Car car = carRepository.findById(id)
//                .orElseThrow(()-> new ResourceNotFoundException("Car with id " + id + " not found"));
//        if (car.getCarParams() != null){
//            carParamsRepository.delete(car.getCarParams());
//        }
//        carParams.setId(car.getId());
//        carParams.setCar(car);
//        car.setCarParams(carParams);
//
//        carParamsRepository.save(carParams);
//        carRepository.save(car);
//    }

    @Override
    public void updateCar(Long id, CarDto carDto) {
        Car car = carRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Car with id " + id + " not found"));
        if(car.getCarParams() != null) {

            carParamsRepository.delete(car.getCarParams());
        }
        Car updatedCar = CarMapper.mapToCar(carDto);
        updatedCar.setId(car.getId());
        carRepository.save(updatedCar);
    }

    @Override
    public void updateCarParam(Long id, CarParams carParams) {
        Car car = carRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Car with id " + id + " not found"));
        if(car.getCarParams() != null) {

            carParamsRepository.delete(car.getCarParams());
        }
        car.setId(car.getId());
        carParams.setId(car.getId());
        car.setCarParams(carParams);
        carParamsRepository.save(carParams);
        carRepository.save(car);
    }

    @Override
    public void deleteCar(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Car with id " + id + " not found"));

        carRepository.deleteById(id);
    }
}
