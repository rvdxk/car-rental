package io.github.rvdxk.carrentalspringproject.controller;

import io.github.rvdxk.carrentalspringproject.dto.CarDto;
import io.github.rvdxk.carrentalspringproject.entity.Car;
import io.github.rvdxk.carrentalspringproject.entity.CarLocation;
import io.github.rvdxk.carrentalspringproject.entity.CarParams;
import io.github.rvdxk.carrentalspringproject.service.CarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {


    private final CarService carService;

    @PostMapping("/add")
    public ResponseEntity<String> addCar(@RequestBody @Valid Car car){
        carService.addCar(car);
        return new ResponseEntity<>("Car successfully added!", HttpStatus.CREATED);
    }

    @PostMapping("/{id}/parameters")
    public ResponseEntity<String> addCarParameters(@PathVariable("id") Long id,
                                                   @RequestBody @Valid CarParams carParams){
        carService.addCarParams(id, carParams);
        return new ResponseEntity<>("Car parameters successfully added!", HttpStatus.CREATED);
    }

    @PostMapping("/{id}/car-location")
    public ResponseEntity<String> addCarLocation(@RequestBody @Valid CarLocation carLocation,
                                                 @PathVariable Long id){
        carService.addCarLocation(id, carLocation);
        return new ResponseEntity<>("Car location successfully added!", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Car>> findAllCars(){
        List<Car> carsList = carService.findAllCars();
        return new ResponseEntity<>(carsList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CarDto> findCarById(@PathVariable("id") Long id){
        CarDto carDto = carService.findCarById(id);
        return new ResponseEntity<>(carDto, HttpStatus.OK);
    }


    @GetMapping("/{id}/car-location")
    public ResponseEntity<CarLocation> findCarLocationById(@PathVariable("id") Long id){
        return new ResponseEntity<>(carService.findCarLocationById(id), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateCar(@PathVariable("id") Long id,
                                            @RequestBody @Valid CarDto carDto){
        carService.updateCar(id, carDto);
        return new ResponseEntity<>("Car successfully updated!", HttpStatus.OK);
    }

    @PutMapping("/{id}/parameters")
    public ResponseEntity<String> updateCarParams(@PathVariable(value = "id", required = true) Long id,
                                                  @RequestBody @Valid CarParams carParams){
        carService.updateCarParam(id, carParams);
        return new ResponseEntity<>("Car parameters successfully updated!", HttpStatus.OK);
    }

    @PutMapping("/{id}/car-location")
    public ResponseEntity<String> updateCarLocation(@PathVariable("id") Long id,
                                                    @RequestBody @Valid CarLocation carLocation){
        carService.updateCarLocation(carLocation, id);
        return new ResponseEntity<>("Car location successfully updated!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCarById(@PathVariable("id") Long id){
        carService.deleteCar(id);
        return new ResponseEntity<>("Car successfully deleted!", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}/car-location")
    public ResponseEntity<String> deleteCarLocation(@PathVariable("id") Long id){
        carService.deleteCarLocation(id);
        return new ResponseEntity<>("Car location successfully deleted!", HttpStatus.NO_CONTENT);
    }


}
