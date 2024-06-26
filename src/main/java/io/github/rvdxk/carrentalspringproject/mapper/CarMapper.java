package io.github.rvdxk.carrentalspringproject.mapper;

import io.github.rvdxk.carrentalspringproject.dto.CarDto;
import io.github.rvdxk.carrentalspringproject.entity.Car;

public class CarMapper {

    public static Car mapToCar(CarDto carDto){
        return Car.builder()
                .id(carDto.getId())
                .make(carDto.getMake())
                .model(carDto.getModel())
                .plateNumber(carDto.getPlateNumber())
                .costPerDay(carDto.getCostPerDay())
                .isAvailable(carDto.isAvailable())
                .carParams(carDto.getCarParams())
                .carLocation(carDto.getCarLocation())
                .build();
    }

    public static CarDto mapToCarDto(Car car){
        return CarDto.builder()
                .id(car.getId())
                .make(car.getMake())
                .model(car.getModel())
                .plateNumber(car.getPlateNumber())
                .costPerDay(car.getCostPerDay())
                .isAvailable(car.isAvailable())
                .carParams(car.getCarParams())
                .carLocation(car.getCarLocation())
                .build();
    }

}
