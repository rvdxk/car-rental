package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.dto.CarDto;
import io.github.rvdxk.carrentalspringproject.entity.Car;
import io.github.rvdxk.carrentalspringproject.entity.CarLocation;
import io.github.rvdxk.carrentalspringproject.entity.CarParams;
import io.github.rvdxk.carrentalspringproject.exception.ResourceNotFoundException;
import io.github.rvdxk.carrentalspringproject.mapper.CarMapper;
import io.github.rvdxk.carrentalspringproject.repository.CarLocationRepository;
import io.github.rvdxk.carrentalspringproject.repository.CarParamsRepository;
import io.github.rvdxk.carrentalspringproject.repository.CarRepository;
import io.github.rvdxk.carrentalspringproject.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarParamsRepository carParamsRepository;
    private final CarLocationRepository carLocationRepository;


    @Override
    public List<Car> findAllCars() {
        List<Car> carsList = carRepository.findAll();

        carsList.forEach(car -> {
            Long id = null;
            if (car.getCarParams() != null) {
                id = car.getCarParams().getId();
            }
            car.setCarParamsId(id);
        });

        carsList.forEach(car -> {
            Long id = null;
            if (car.getCarLocation() != null) {
                id = car.getCarLocation().getId();
            }
            car.setCarLocationId(id);
        });

        return carsList;
    }

    @Override
    public CarDto findCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car with id " + id + " not found"));

       return CarMapper.mapToCarDto(car);
    }

    @Override
    public CarLocation findCarLocationById(Long id) {
        CarLocation carLocation = carLocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CarLocation with id " + id + " not found"));
        return carLocation;
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
    public void addCarLocation(Long id, CarLocation carLocation) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car with id " + id + " not found"));
        if(car.getCarLocation() != null) {

            carLocationRepository.delete(car.getCarLocation());
        }
        carLocationRepository.save(carLocation);
        car.setCarLocation(carLocation);
        carRepository.save(car);
    }

    @Override
    public void updateCar(Long id, CarDto carDto) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car with id " + id + " not found"));

        car.setMake(carDto.getMake());
        car.setModel(carDto.getModel());
        car.setPlateNumber(carDto.getPlateNumber());
        car.setCostPerDay(carDto.getCostPerDay());
        car.setAvailable(carDto.isAvailable());

        carRepository.save(car);
    }

    @Override
    public void updateCarParam(Long id, CarParams carParams) {
        Optional<CarParams> existingCarParamsOptional = carParamsRepository.findById(id);
        if (existingCarParamsOptional.isPresent()) {

            CarParams updateParams = existingCarParamsOptional.get();

            updateParams.setMake(carParams.getMake());
            updateParams.setModel(carParams.getModel());
            updateParams.setProdYear(carParams.getProdYear());
            updateParams.setType(carParams.getType());
            updateParams.setNumberOfDoors(carParams.getNumberOfDoors());
            updateParams.setNumberOfSeats(carParams.getNumberOfSeats());
            updateParams.setGearbox(carParams.getGearbox());
            updateParams.setDriveWheels(carParams.getDriveWheels());

            carParamsRepository.save(updateParams);

            Car car = carRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Car with id " + id + " not found"));
            car.setCarParams(updateParams);
            carRepository.save(car);
        } else {
            throw new ResourceNotFoundException("CarParams with id " + id + " not found");
        }
    }

    @Override
    public void updateCarLocation(CarLocation carLocation, Long id) {

        Optional<CarLocation>  existingCarLocationOptional = carLocationRepository.findById(id);
        if (existingCarLocationOptional.isPresent()) {
            CarLocation updateCarLocation = existingCarLocationOptional.get();

            updateCarLocation.setStreet(carLocation.getStreet());
            updateCarLocation.setHouseNumber(carLocation.getHouseNumber());
            updateCarLocation.setCity(carLocation.getCity());
            updateCarLocation.setPostalCode(carLocation.getPostalCode());
            updateCarLocation.setCountry(carLocation.getCountry());

            carLocationRepository.save(updateCarLocation);

            Car car = carRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Car with id " + id + " not found"));
            car.setCarLocation(updateCarLocation);
            carRepository.save(car);
        }
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Car with id " + id + " not found"));

        carRepository.deleteById(id);
    }

    @Override
    public void deleteCarLocation(Long id) {
        Car car = carRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Car with id " + id + " not found"));
        if (car.getCarLocation() != null) {
            CarLocation carLocation = car.getCarLocation();
            car.setCarLocation(null);
            carRepository.save(car);
            carLocationRepository.delete(carLocation);
        } else {
            throw new ResourceNotFoundException("Car with id " + id + " does not have a location assigned.");
        }
    }
}
