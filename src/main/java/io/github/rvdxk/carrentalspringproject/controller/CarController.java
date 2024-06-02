package io.github.rvdxk.carrentalspringproject.controller;

import io.github.rvdxk.carrentalspringproject.dto.CarDto;
import io.github.rvdxk.carrentalspringproject.entity.Car;
import io.github.rvdxk.carrentalspringproject.entity.CarParams;
import io.github.rvdxk.carrentalspringproject.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    public CarService carService;


    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars(){
        List<Car> carsList = carService.getAllCars();
        return new ResponseEntity<>(carsList, HttpStatus.OK);
    }

    @PostMapping("/cars/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addCar(@RequestBody @Valid CarDto carDto){
        carService.addCar(carDto);
        return "Car successfully added!";
    }

    @PostMapping("/cars/{id}/parameters")
    @ResponseStatus(HttpStatus.CREATED)
    public String addCarParameters(@PathVariable Long id,
                                   @RequestBody @Valid CarParams carParams){
        carService.addCarParams(id, carParams);
        return "Car parameters successfully added!";
    }

    @GetMapping("/cars/{id}")
    public CarDto getCarById(@PathVariable("id") Long id){
        CarDto carDto = carService.getCarById(id);
        return carDto;
    }

    @PutMapping("/cars/{id}")
    public String updateCar(@PathVariable("id") Long id,
                            @RequestBody @Valid CarDto carDto){
        carService.updateCar(id, carDto);
        return "Car successfully updated!";
    }

    @PutMapping("/cars/{id}/parameters")
    public String updateCarParams(@PathVariable(value = "id", required = true) Long id,
                                  @RequestBody @Valid CarParams carParams){
        carService.updateCarParam(id, carParams);
        return "Car parameters successfully updated!";
    }

    @DeleteMapping("/cars/{id}")
    public String deleteCarById(@PathVariable("id") Long id){
        carService.deleteCar(id);
        return "Car successfully deleted!";
    }

}
