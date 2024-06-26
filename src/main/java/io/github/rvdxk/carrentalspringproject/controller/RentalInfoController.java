package io.github.rvdxk.carrentalspringproject.controller;

import io.github.rvdxk.carrentalspringproject.dto.RentalInfoDto;
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
@RequestMapping("/rental-info")
public class RentalInfoController {

    @Autowired
    RentalInfoService rentalInfoService;

    @GetMapping
    public ResponseEntity<List<RentalInfoDto>> findAllRentalsInfo(){
        List<RentalInfoDto> rentalsInfoList = rentalInfoService.findAllRentalsInfo();
        return new ResponseEntity<>(rentalsInfoList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RentalInfoDto> findRentalInfoById(@PathVariable("id") Long infoId){
        RentalInfoDto rentalInfo = rentalInfoService.findRentalInfoById(infoId);
        return new ResponseEntity<>(rentalInfo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addRentalInfo(@RequestBody @Valid RentalInfo rentalInfo){
        rentalInfoService.addRentalInfo(rentalInfo);
        return new ResponseEntity<>("Rental information successfully added!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRentalInfo(@PathVariable("id") Long rentalId,
                                                   @RequestBody @Valid RentalInfo rentalInfo){
        rentalInfo.setId(rentalId);
        rentalInfoService.updateRentalInfo(rentalId, rentalInfo);
        return new ResponseEntity<>("Rental information successfully updated!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRentalInfo(@PathVariable("id") Long infoId){
        rentalInfoService.deleteRentalInfo(infoId);
        return new ResponseEntity<>("Rental information successfully deleted!", HttpStatus.NO_CONTENT;
    }
}
