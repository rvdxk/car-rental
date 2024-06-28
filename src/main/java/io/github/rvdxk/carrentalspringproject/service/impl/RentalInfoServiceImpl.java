package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.constant.PaymentStatus;
import io.github.rvdxk.carrentalspringproject.constant.RentalStatus;
import io.github.rvdxk.carrentalspringproject.dto.RentalInfoDto;
import io.github.rvdxk.carrentalspringproject.entity.RentalInfo;
import io.github.rvdxk.carrentalspringproject.exception.ResourceNotFoundException;
import io.github.rvdxk.carrentalspringproject.mapper.RentalInfoMapper;
import io.github.rvdxk.carrentalspringproject.repository.RentalInfoRepository;
import io.github.rvdxk.carrentalspringproject.service.PaymentService;
import io.github.rvdxk.carrentalspringproject.service.RentalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalInfoServiceImpl implements RentalInfoService {

    private final RentalInfoRepository rentalInfoRepository;


    @Override
    public List<RentalInfoDto> findAllRentalsInfo() {
        List<RentalInfo> rentalInfoList = rentalInfoRepository.findAll();
        List<RentalInfoDto> rentalInfoDtoList = rentalInfoList.stream()
                .map((rental) -> RentalInfoMapper.mapToRentalInfoDto(rental))
                .collect(Collectors.toUnmodifiableList());

        return rentalInfoDtoList;
    }

    @Override
    public RentalInfoDto findRentalInfoById(Long id) {
        RentalInfo rentalInfo = rentalInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental information with id: " + id + " not found"));

        return RentalInfoMapper.mapToRentalInfoDto(rentalInfo);
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

        updateInfo.setId(id);
        updateInfo.setCarId(rentalInfo.getCarId());
        updateInfo.setCarLocationId(rentalInfo.getCarLocationId());
        updateInfo.setCustomerId(rentalInfo.getCustomerId());
        updateInfo.setPayment(rentalInfo.getPayment());
        updateInfo.setRentalDate(rentalInfo.getRentalDate());
        updateInfo.setReturnDate(rentalInfo.getReturnDate());
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
        if ((rentalDate.isEqual(now)) ||
                (now.isAfter(rentalDate) && now.isBefore(returnDate))) {
            return RentalStatus.IN_PROGRESS;
        }
        if ((now.isAfter(returnDate)) && (now.isAfter(rentalDate))) {
            return RentalStatus.COMPLETED;
        }
        return RentalStatus.UNKNOWN;
    }
}
