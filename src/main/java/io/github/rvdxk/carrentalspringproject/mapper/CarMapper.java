package io.github.rvdxk.carrentalspringproject.mapper;

import io.github.rvdxk.carrentalspringproject.dto.CarDto;
import io.github.rvdxk.carrentalspringproject.entity.Car;
import io.github.rvdxk.carrentalspringproject.entity.CarParams;

public class CarMapper {

    public static CarDto mapToCarDto(Car car){
        CarDto carDto = new CarDto(
                car.getId(),
                car.getMake(),
                car.getModel(),
                car.getPlateNumber(),
                car.getCostPerHour(),
                car.isAvailable(),
                car.getCarParams()
        );
        return carDto;
    }

    public static Car mapToCar(CarDto carDto){
        Car car = new Car(
                carDto.getId(),
                carDto.getMake(),
                carDto.getModel(),
                carDto.getPlateNumber(),
                carDto.getCostPerHour(),
                carDto.isAvailable(),
                carDto.getCarParams()
        );
        return car;
    }
}
