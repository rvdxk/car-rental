package io.github.rvdxk.carrentalspringproject.service;

import io.github.rvdxk.carrentalspringproject.entity.RentalInfo;

import java.util.List;

public interface RentalInfoService {

    List<RentalInfo> findAllRentalsInfo();
    RentalInfo findRentalInfoById(Long id);
    void addRentalInfo(RentalInfo rentalInfo);
    void updateRentalInfo(Long id, RentalInfo rentalInfo);
    void deleteRentalInfo(Long id);

}
