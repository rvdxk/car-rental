package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.constant.RentalStatus;
import io.github.rvdxk.carrentalspringproject.entity.RentalInfo;
import io.github.rvdxk.carrentalspringproject.exception.ResourceNotFoundException;
import io.github.rvdxk.carrentalspringproject.repository.RentalInfoRepository;
import io.github.rvdxk.carrentalspringproject.service.RentalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalInfoServiceImpl implements RentalInfoService {

    private final RentalInfoRepository rentalInfoRepository;


    @Override
    public List<RentalInfo> getAllRentalsInfo() {
        List<RentalInfo> getAllRentalsInfo = rentalInfoRepository.findAll();
        return getAllRentalsInfo;
    }

    @Override
    public RentalInfo getRentalInfoById(Long id) {
        RentalInfo rentalInfo = rentalInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental information with id: " + id + " not found"));
        return rentalInfo;
    }

    @Override
    public void addRentalInfo(RentalInfo rentalInfo) {
        rentalInfo.setRentalStatus(addRentalStatus(rentalInfo));
        rentalInfoRepository.save(rentalInfo);
    }

    @Override
    public void updateRentalInfo(Long id, RentalInfo rentalInfo) {
        RentalInfo updateInfo = rentalInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental information with id: " + id + " not found"));

        updateInfo.setCarId(rentalInfo.getCarId());
        updateInfo.setRentalDate(rentalInfo.getRentalDate());
        updateInfo.setReturnDate(rentalInfo.getReturnDate());
        updateInfo.setTotalCost(rentalInfo.getTotalCost());
        updateInfo.setRentalStatus(addRentalStatus(rentalInfo));

        rentalInfoRepository.save(updateInfo);
    }

    @Override
    public void deleteRentalInfo(Long id) {
        RentalInfo rentalInfo = rentalInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental information with id: " + id + " not found"));
        rentalInfoRepository.deleteById(id);
    }

    public RentalStatus addRentalStatus(RentalInfo rentalInfo) {
        if (rentalInfo == null) {
            return RentalStatus.AVAILABLE;
        }

        LocalDate now = LocalDate.now();
        LocalDate rentalDate = rentalInfo.getRentalDate();
        LocalDate returnDate = rentalInfo.getReturnDate();

        if (rentalDate == null || returnDate == null) {
            return RentalStatus.AVAILABLE;
        }
        if (rentalDate.isEqual(now) || returnDate.isEqual(now)) {
            return RentalStatus.IN_PROGRESS;
        }
        if (rentalDate.isAfter(now)) {
            return RentalStatus.RESERVED;
        } else if (rentalDate.isBefore(now) && returnDate.isAfter(now)) {
            return RentalStatus.IN_PROGRESS;
        } else if (returnDate.isBefore(now)) {
            return RentalStatus.EXPIRED;
        } else {
            return RentalStatus.COMPLETED;
        }
    }
}
