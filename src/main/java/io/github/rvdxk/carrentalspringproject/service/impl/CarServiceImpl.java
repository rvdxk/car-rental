package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.dto.CarDto;
import io.github.rvdxk.carrentalspringproject.entity.Car;
import io.github.rvdxk.carrentalspringproject.entity.CarParams;
import io.github.rvdxk.carrentalspringproject.exception.ResourceNotFoundException;
import io.github.rvdxk.carrentalspringproject.mapper.CarMapper;
import io.github.rvdxk.carrentalspringproject.repository.CarParamsRepository;
import io.github.rvdxk.carrentalspringproject.repository.CarRepository;
import io.github.rvdxk.carrentalspringproject.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarParamsRepository carParamsRepository;

    public CarServiceImpl(CarRepository carRepository, CarParamsRepository carParamsRepository) {
        this.carRepository = carRepository;
        this.carParamsRepository = carParamsRepository;
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> carsList = carRepository.findAll();

        carsList.forEach(car -> {
            Long id = null;
            if (car.getCarParams() != null) {
                id = car.getCarParams().getId();
            }
            car.setCarParamsId(id);
        });
        return carsList;
    }

    @Override
    public CarDto getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car with id " + id + " not found"));

        CarParams carParams = car.getCarParams();
        if (carParams == null) {
            throw new ResourceNotFoundException("Car with id " + id + " not found");
        }
        car.setId(car.getId());
        car.setMake(car.getMake());
        car.setModel(car.getModel());
        car.setPlateNumber(car.getPlateNumber());
        car.setCostPerHour(car.getCostPerHour());
        car.setAvailable(car.isAvailable());
        car.setCarParams(carParams);
       return CarMapper.mapToCarDto(car);
    }

    @Override
    public void addCar(CarDto carDto) {
        Car car = CarMapper.mapToCar(carDto);
        carRepository.save(car);
    }

    @Override
    public void addCarParams(Long id, CarParams carParams) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car with id " + id + " not found"));
        if(car.getCarParams() != null) {

            carParamsRepository.delete(car.getCarParams());
        }
        carParamsRepository.save(carParams);
        car.setCarParams(carParams);
        carRepository.save(car);
    }


    @Override
    public void updateCar(Long id, CarDto carDto) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car with id " + id + " not found"));

        car.setMake(carDto.getMake());
        car.setModel(carDto.getModel());
        car.setPlateNumber(carDto.getPlateNumber());
        car.setCostPerHour(carDto.getCostPerHour());
        car.setAvailable(carDto.isAvailable());

        if (carDto.getCarParams() != null) {
            CarParams carParams = car.getCarParams();
            if (carParams == null) {
                carParams = new CarParams();
                carParams.setId(id);
            }
            carParams.setMake(carDto.getCarParams().getMake());
            carParams.setModel(carDto.getCarParams().getModel());
            carParams.setProdYear(carDto.getCarParams().getProdYear());
            carParams.setType(carDto.getCarParams().getType());
            carParams.setNumberOfDoors(carDto.getCarParams().getNumberOfDoors());
            carParams.setNumberOfSeats(carDto.getCarParams().getNumberOfSeats());
            carParams.setGearbox(carDto.getCarParams().getGearbox());
            carParams.setDriveWheels(carDto.getCarParams().getDriveWheels());

            car.setCarParams(carParams);
        } else {
            car.setCarParams(null);
        }
        carRepository.save(car);
    }

    @Override
    public void updateCarParam(Long id, CarParams carParams) {
        Optional<CarParams> existingCarParamsOptional = carParamsRepository.findById(id);
        if (existingCarParamsOptional.isPresent()) {

            CarParams existingCarParams = existingCarParamsOptional.get();

            existingCarParams.setMake(carParams.getMake());
            existingCarParams.setModel(carParams.getModel());
            existingCarParams.setProdYear(carParams.getProdYear());
            existingCarParams.setType(carParams.getType());
            existingCarParams.setNumberOfDoors(carParams.getNumberOfDoors());
            existingCarParams.setNumberOfSeats(carParams.getNumberOfSeats());
            existingCarParams.setGearbox(carParams.getGearbox());
            existingCarParams.setDriveWheels(carParams.getDriveWheels());

            carParamsRepository.save(existingCarParams);

            Car car = carRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Car with id " + id + " not found"));
            car.setCarParams(existingCarParams);
            carRepository.save(car);
        } else {
            throw new ResourceNotFoundException("CarParams with id " + id + " not found");
        }
    }

    @Override
    public void deleteCar(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Car with id " + id + " not found"));

        carRepository.deleteById(id);
    }
}
