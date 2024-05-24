package io.github.rvdxk.carrentalspringproject.mapper;

import io.github.rvdxk.carrentalspringproject.dto.CarDto;
import io.github.rvdxk.carrentalspringproject.entity.Car;
import io.github.rvdxk.carrentalspringproject.entity.CarParams;

public class CarMapper {

    public static Car mapToCar(CarDto carDto){
        return Car.builder()
                .id(carDto.getId())
                .make(carDto.getMake())
                .model(carDto.getModel())
                .plateNumber(carDto.getPlateNumber())
                .costPerHour(carDto.getCostPerHour())
                .isAvailable(carDto.isAvailable())
                .carParams(carDto.getCarParams())
                .build();
    }

    public static CarDto mapToCarDto(Car car){
        return CarDto.builder()
                .id(car.getId())
                .make(car.getMake())
                .model(car.getModel())
                .plateNumber(car.getPlateNumber())
                .costPerHour(car.getCostPerHour())
                .isAvailable(car.isAvailable())
                .carParams(car.getCarParams())
                .build();
    }

}
