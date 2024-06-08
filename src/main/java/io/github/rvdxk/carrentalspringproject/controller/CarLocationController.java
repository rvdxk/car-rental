package io.github.rvdxk.carrentalspringproject.controller;

import io.github.rvdxk.carrentalspringproject.entity.CarLocation;
import io.github.rvdxk.carrentalspringproject.service.CarLocationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CarLocationController {

    private final CarLocationService carLocationService;

    @PostMapping("/car-location")
    @ResponseStatus(HttpStatus.CREATED)
    public String addCarLocation(@RequestBody @Valid CarLocation carLocation){
        carLocationService.addCarLocation(carLocation);
        return "Car location successfully added!";
    }

    @GetMapping("/car-location")
    public ResponseEntity<List<CarLocation>> getAllCarLocation(){
        List<CarLocation> getAllCarLocation = carLocationService.getAllCarLocation();
        return new ResponseEntity<>(getAllCarLocation, HttpStatus.OK);
    }

    @GetMapping("/car-location/{id}")
    public CarLocation getCarLocationById(@PathVariable("id") Long id){
       return carLocationService.getCarLocationById(id);
    }

    @PutMapping("/car-location/{id}")
    public String updateCarLocation(@PathVariable("id") Long id,
                                    @RequestBody @Valid CarLocation carLocation){
        carLocationService.getCarLocationById(id);
        carLocationService.updateCarLocation(carLocation, id);
        return "Car location successfully updated!";
    }

    @DeleteMapping("/car-location/{id}")
    public String deleteCarLocation(@PathVariable("id") Long id){
        carLocationService.deleteCarLocation(id);
        return "Car location successfully deleted!";
    }

}
