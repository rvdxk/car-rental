package io.github.rvdxk.carrentalspringproject.controller;

import io.github.rvdxk.carrentalspringproject.entity.RentalInfo;
import io.github.rvdxk.carrentalspringproject.service.RentalInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/rental/info")
public class RentalInfoController {

    @Autowired
    RentalInfoService rentalInfoService;

    @GetMapping
    public ResponseEntity<List<RentalInfo>> findAllRentalsInfo(){
        List<RentalInfo> rentalsInfoList = rentalInfoService.findAllRentalsInfo();
        return new ResponseEntity<>(rentalsInfoList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public RentalInfo findRentalInfoById(@PathVariable("id") Long infoId){
        RentalInfo rentalInfo = rentalInfoService.findRentalInfoById(infoId);
        return rentalInfo;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addRentalInfo(@RequestBody @Valid RentalInfo rentalInfo){
        rentalInfoService.addRentalInfo(rentalInfo);
        return "Rental information successfully added!";
    }

    @PutMapping("/{id}")
    public String updateRentalInfo(@PathVariable("id") Long rentalId,
                                   @RequestBody @Valid RentalInfo rentalInfo){
        rentalInfo.setId(rentalId);
        rentalInfoService.updateRentalInfo(rentalId, rentalInfo);
        return "Rental information successfully updated!";
    }

    @DeleteMapping("/{id}")
    public String deleteRentalInfo(@PathVariable("id") Long infoId){
        rentalInfoService.deleteRentalInfo(infoId);
        return "Rental information successfully deleted!";
    }
}
