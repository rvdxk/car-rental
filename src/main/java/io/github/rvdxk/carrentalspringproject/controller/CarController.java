package io.github.rvdxk.carrentalspringproject.controller;

import io.github.rvdxk.carrentalspringproject.dto.CarDto;
import io.github.rvdxk.carrentalspringproject.entity.Car;
import io.github.rvdxk.carrentalspringproject.entity.CarLocation;
import io.github.rvdxk.carrentalspringproject.entity.CarParams;
import io.github.rvdxk.carrentalspringproject.service.CarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cars")
public class CarController {

    @Autowired
    public CarService carService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addCar(@RequestBody @Valid CarDto carDto){
        carService.addCar(carDto);
        return "Car successfully added!";
    }

    @PostMapping("/{id}/parameters")
    @ResponseStatus(HttpStatus.CREATED)
    public String addCarParameters(@PathVariable("id") Long id,
                                   @RequestBody @Valid CarParams carParams){
        carService.addCarParams(id, carParams);
        return "Car parameters successfully added!";
    }

    @PostMapping("/{id}/car-location")
    @ResponseStatus(HttpStatus.CREATED)
    public String addCarLocation(@RequestBody @Valid CarLocation carLocation,
                                 @PathVariable Long id){
        carService.addCarLocation(id, carLocation);
        return "Car location successfully added!";
    }

    @GetMapping
    public ResponseEntity<List<Car>> findAllCars(){
        List<Car> carsList = carService.findAllCars();
        return new ResponseEntity<>(carsList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public CarDto findCarById(@PathVariable("id") Long id){
        CarDto carDto = carService.findCarById(id);
        return carDto;
    }


    @GetMapping("/{id}/car-location")
    public CarLocation findCarLocationById(@PathVariable("id") Long id){
        return carService.findCarLocationById(id);
    }


    @PutMapping("/{id}")
    public String updateCar(@PathVariable("id") Long id,
                            @RequestBody @Valid CarDto carDto){
        carService.updateCar(id, carDto);
        return "Car successfully updated!";
    }

    @PutMapping("/{id}/parameters")
    public String updateCarParams(@PathVariable(value = "id", required = true) Long id,
                                  @RequestBody @Valid CarParams carParams){
        carService.updateCarParam(id, carParams);
        return "Car parameters successfully updated!";
    }

    @PutMapping("/{id}/car-location")
    public String updateCarLocation(@PathVariable("id") Long id,
                                    @RequestBody @Valid CarLocation carLocation){
        carService.updateCarLocation(carLocation, id);
        return "Car location successfully updated!";
    }

    @DeleteMapping("/{id}")
    public String deleteCarById(@PathVariable("id") Long id){
        carService.deleteCar(id);
        return "Car successfully deleted!";
    }

    @DeleteMapping("/{id}/car-location")
    public String deleteCarLocation(@PathVariable("id") Long id){
        carService.deleteCarLocation(id);
        return "Car location successfully deleted!";
    }


}
