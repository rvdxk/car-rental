package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.constant.Country;
import io.github.rvdxk.carrentalspringproject.dto.CarDto;
import io.github.rvdxk.carrentalspringproject.entity.Car;
import io.github.rvdxk.carrentalspringproject.entity.CarLocation;
import io.github.rvdxk.carrentalspringproject.entity.CarParams;
import io.github.rvdxk.carrentalspringproject.exception.ResourceNotFoundException;
import io.github.rvdxk.carrentalspringproject.repository.CarLocationRepository;
import io.github.rvdxk.carrentalspringproject.repository.CarParamsRepository;
import io.github.rvdxk.carrentalspringproject.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static io.github.rvdxk.carrentalspringproject.constant.Gearbox.AUTOMATIC;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    CarParamsRepository carParamsRepository;

    @Mock
    CarLocationRepository carLocationRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private Car car1;
    private Car car2;

    private CarParams carParams;

    private CarLocation carLocation;

    @BeforeEach
    void setUp(){

        carParams = CarParams.builder()
                .id(1L)
                .make("Skoda")
                .model("Octavia")
                .prodYear(2019)
                .type("sedan")
                .numberOfDoors(4)
                .numberOfSeats(5)
                .gearbox(AUTOMATIC)
                .driveWheels("FWD")
                .build();

        carLocation = CarLocation.builder()
                .id(1L)
                .street("Wielkopolska")
                .houseNumber(203L)
                .city("Gdynia")
                .postalCode("81-800")
                .country(Country.POLAND)
                .build();

        car1 = Car.builder()
                .id(1L)
                .make("Skoda")
                .model("Octavia")
                .plateNumber("GA 123AA")
                .costPerDay(100)
                .isAvailable(true)
                .carParams(carParams)
                .carLocation(carLocation)
                .build();

        car2 = Car.builder()
                .id(1L)
                .make("Skoda")
                .model("Octavia")
                .plateNumber("GA 123BB")
                .costPerDay(100)
                .isAvailable(true)
                .carParams(carParams)
                .carLocation(carLocation)
                .build();

    }


    @Test
    void findAllCars_ShouldReturnAllCarsWithMappedCarParamsAndCarLocationIds() {

        List<Car> carList = Arrays.asList(car1, car2);
        when(carRepository.findAll()).thenReturn(carList);

        List<Car> result = carService.findAllCars();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(carRepository, times(1)).findAll();

        Car resultCar1 = result.get(0);
        assertEquals(carParams.getId(), resultCar1.getCarParamsId());
        assertEquals(carLocation.getId(), resultCar1.getCarLocationId());

        Car resultCar2 = result.get(1);
        assertEquals(carParams.getId(), resultCar2.getCarParamsId());
        assertEquals(carLocation.getId(), resultCar2.getCarLocationId());

        assertEquals(2, result.size());
    }

    @Test
    void findCarById_ShouldReturnCarWithGeneralInformation() {
        when(carRepository.findById(car1.getId())).thenReturn(Optional.of(car1));

         CarDto result = carService.findCarById(car1.getId());

         assertNotNull(result);

         verify(carRepository,times(1)).findById(car1.getId());

        assertEquals(car1.getId(), result.getId());
        assertEquals(car1.getMake(), result.getMake());
        assertEquals(car1.getModel(), result.getModel());
        assertEquals(car1.getPlateNumber(), result.getPlateNumber());
        assertEquals(car1.getCostPerDay(), result.getCostPerDay());
        assertEquals(car1.isAvailable(), result.isAvailable());
    }

    @Test
    void findCarLocationById_ShouldReturnCarLocationByCarId() {
        when(carLocationRepository.findById(carLocation.getId())).thenReturn(Optional.of(carLocation));

        CarLocation result = carService.findCarLocationById(carLocation.getId());

        assertNotNull(result);

        verify(carLocationRepository, times(1)).findById(carLocation.getId());

        assertEquals(carLocation.getId(), result.getId());
        assertEquals(carLocation.getStreet(), result.getStreet());
        assertEquals(carLocation.getHouseNumber(), result.getHouseNumber());
        assertEquals(carLocation.getCity(), result.getCity());
        assertEquals(carLocation.getPostalCode(), result.getPostalCode());
        assertEquals(carLocation.getCountry(), result.getCountry());
    }

    @Test
    void addCar_ShouldSaveCarWithGeneralInformation() {

        when(carRepository.save(car1)).thenReturn(car1);

        carService.addCar(car1);

        verify(carRepository, times(1)).save(car1);
    }

    @Test
    void addCarParams_ShouldAddParametersToCarById() {

        when(carRepository.findById(car1.getId())).thenReturn(Optional.of(car1));
        when(carParamsRepository.save(carParams)).thenReturn(carParams);

        carService.addCarParams(car1.getId(), carParams);

        verify(carParamsRepository, times(1)).save(carParams);
    }

    @Test
    public void addCarParamsCarNotFound() {

        when(carRepository.findById(car1.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            carService.addCarParams(car1.getId(), new CarParams());
        });

        assertEquals("Car with id " + car1.getId() + " not found", exception.getMessage());

        verify(carParamsRepository, never()).save(any());
    }

    @Test
    void addCarLocation_ShouldAddCarLocationToCarId() {


        when(carRepository.findById(car1.getId())).thenReturn(Optional.of(car1));
        when(carLocationRepository.save(carLocation)).thenReturn(carLocation);

        carService.addCarLocation(car1.getId(), carLocation);

        verify(carLocationRepository, times(1)).save(carLocation);
    }

    @Test
    public void addCarLocationCarNotFound() {

        when(carRepository.findById(car1.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            carService.addCarParams(car1.getId(), new CarParams());
        });

        assertEquals("Car with id " + car1.getId() + " not found", exception.getMessage());

        verify(carLocationRepository, never()).save(any());
    }

    @Test
    void updateCar_ShouldUpdateGeneralInformation() {


        CarDto updateCarDto = CarDto.builder()
                .make("Skoda")
                .model("Superb")
                .plateNumber("GA 999CC")
                .costPerDay(160)
                .isAvailable(true)
                .build();

        when(carRepository.findById(car1.getId())).thenReturn(Optional.of(car1));

        carService.updateCar(car1.getId(), updateCarDto);


        verify(carRepository, times(1)).findById(car1.getId());
        verify(carRepository, times(1)).save(car1);

        assertEquals(updateCarDto.getMake(), car1.getMake());
        assertEquals(updateCarDto.getModel(), car1.getModel());
        assertEquals(updateCarDto.getPlateNumber(), car1.getPlateNumber());
        assertEquals(updateCarDto.getCostPerDay(), car1.getCostPerDay());
        assertEquals(updateCarDto.isAvailable(), car1.isAvailable());

    }

    @Test
    void updateCarParam_ShouldUpdateCarParameters() {

        CarParams updateCarParams = CarParams.builder()
                .id(1L)
                .make("Skoda")
                .model("Superb")
                .prodYear(2021)
                .type("sedan")
                .numberOfDoors(4)
                .numberOfSeats(5)
                .gearbox(AUTOMATIC)
                .driveWheels("AWD")
                .build();

        when(carParamsRepository.findById(carParams.getId())).thenReturn(Optional.of(carParams));
        when(carRepository.findById(car1.getId())).thenReturn(Optional.of(car1));


        carService.updateCarParam(carParams.getId(), updateCarParams);

        verify(carParamsRepository, times(1)).findById(carParams.getId());
        verify(carParamsRepository, times(1)).save(carParams);

        verify(carRepository, times(1)).findById(updateCarParams.getId());
        verify(carRepository, times(1)).save(car1);

        assertEquals(updateCarParams.getMake(), carParams.getMake());
        assertEquals(updateCarParams.getModel(), carParams.getModel());
        assertEquals(updateCarParams.getProdYear(), carParams.getProdYear());
        assertEquals(updateCarParams.getType(), carParams.getType());
        assertEquals(updateCarParams.getNumberOfDoors(), carParams.getNumberOfDoors());
        assertEquals(updateCarParams.getNumberOfSeats(), carParams.getNumberOfSeats());
        assertEquals(updateCarParams.getGearbox(), carParams.getGearbox());
        assertEquals(updateCarParams.getDriveWheels(), carParams.getDriveWheels());
    }

    @Test
    void updateCarLocation_ShouldUpdateCarLocation() {

        CarLocation updateCarLocation = CarLocation.builder()
                .id(1L)
                .street("Grunwaldzka")
                .houseNumber(183L)
                .city("Gdansk")
                .postalCode("80-800")
                .country(Country.POLAND)
                .build();

        when(carLocationRepository.findById(carLocation.getId())).thenReturn(Optional.of(carLocation));
        when(carRepository.findById(car1.getId())).thenReturn(Optional.of(car1));

        carService.updateCarLocation(updateCarLocation, carLocation.getId());

        verify(carLocationRepository, times(1)).findById(carLocation.getId());
        verify(carLocationRepository, times(1)).save(carLocation);

        verify(carRepository, times(1)).findById(updateCarLocation.getId());
        verify(carRepository, times(1)).save(car1);

        assertEquals(updateCarLocation.getStreet(), carLocation.getStreet());
        assertEquals(updateCarLocation.getHouseNumber(), carLocation.getHouseNumber());
        assertEquals(updateCarLocation.getCity(), carLocation.getCity());
        assertEquals(updateCarLocation.getPostalCode(), carLocation.getPostalCode());
        assertEquals(updateCarLocation.getCountry(), carLocation.getCountry());
    }

    @Test
    void deleteCar_ShouldDeleteCarWithCarParameters() {

        when(carRepository.findById(car1.getId())).thenReturn(Optional.of(car1));

        carService.deleteCar(car1.getId());

        verify(carRepository, times(1)).findById(car1.getId());
        verify(carRepository, times(1)).deleteById(car1.getId());

    }

    @Test
    void deleteCarLocation_ShouldDeleteOnlyCarLocation() {

        when(carRepository.findById(car1.getId())).thenReturn(Optional.of(car1));

        carService.deleteCarLocation(car1.getId());

        verify(carRepository, times(1)).findById(car1.getId());
        verify(carRepository, times(1)).save(car1);
        verify(carLocationRepository, times(1)).delete(carLocation);

    }
}