package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.entity.CarLocation;
import io.github.rvdxk.carrentalspringproject.exception.ResourceNotFoundException;
import io.github.rvdxk.carrentalspringproject.repository.CarLocationRepository;
import io.github.rvdxk.carrentalspringproject.service.CarLocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarLocationServiceImpl implements CarLocationService {

    private final CarLocationRepository carLocationRepository;

    public CarLocationServiceImpl(CarLocationRepository carLocationRepository) {
        this.carLocationRepository = carLocationRepository;
    }

    @Override
    public List<CarLocation> getAllCarLocation() {
        List<CarLocation> getAllCarLocation = carLocationRepository.findAll();
        return getAllCarLocation;
    }

    @Override
    public CarLocation getCarLocationById(Long id) {
        CarLocation carLocation = carLocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CarLocation with id " + id + " not found"));
        return carLocation;
    }

    @Override
    public void addCarLocation(CarLocation carLocation) {
        carLocationRepository.save(carLocation);
    }

    @Override
    public void updateCarLocation(CarLocation carLocation, Long id) {
        CarLocation existingCarLocation = carLocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CarLocation with id " + id + " not found"));

        existingCarLocation.setStreet(carLocation.getStreet());
        existingCarLocation.setHouseNumber(carLocation.getHouseNumber());
        existingCarLocation.setCity(carLocation.getCity());
        existingCarLocation.setPostalCode(carLocation.getPostalCode());
        existingCarLocation.setCountry(carLocation.getCountry());

        carLocationRepository.save(existingCarLocation);
    }

    @Override
    public void deleteCarLocation(Long id) {
        carLocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CarLocation with id " + id + " not found"));
        carLocationRepository.deleteById(id);
    }
}
