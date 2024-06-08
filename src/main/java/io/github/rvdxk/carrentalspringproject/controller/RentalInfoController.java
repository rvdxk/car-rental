package io.github.rvdxk.carrentalspringproject.controller;

import io.github.rvdxk.carrentalspringproject.entity.RentalInfo;
import io.github.rvdxk.carrentalspringproject.service.RentalInfoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class RentalInfoController {

    @Autowired
    RentalInfoService rentalInfoService;

    @GetMapping("/rental/info")
    public ResponseEntity<List<RentalInfo>> getAllRentalsInfo(){
        List<RentalInfo> rentalsInfoList = rentalInfoService.getAllRentalsInfo();
        return new ResponseEntity<>(rentalsInfoList, HttpStatus.OK);
    }


    @GetMapping("/rental/info/{id}")
    public RentalInfo getRentalInfoById(@PathVariable("id") Long infoId){
        RentalInfo rentalInfo = rentalInfoService.getRentalInfoById(infoId);
        return rentalInfo;
    }

    @PostMapping("/rental/info")
    @ResponseStatus(HttpStatus.CREATED)
    public String addRentalInfo(@RequestBody @Valid RentalInfo rentalInfo){
        rentalInfoService.addRentalInfo(rentalInfo);
        return "Rental information successfully added!";
    }

    @PutMapping("/rental/info/{id}")
    public String updateRentalInfo(@PathVariable("id") Long rentalId,
                                   @RequestBody @Valid RentalInfo rentalInfo){
        rentalInfo.setId(rentalId);
        rentalInfoService.updateRentalInfo(rentalId, rentalInfo);
        return "Rental information successfully updated!";
    }

    @DeleteMapping("/rental/info/{id}")
    public String deleteRentalInfo(@PathVariable("id") Long infoId){
        rentalInfoService.deleteRentalInfo(infoId);
        return "Rental information successfully deleted!";
    }
}
