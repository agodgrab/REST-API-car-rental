package com.agodgrab.carrental.mapper;

import com.agodgrab.carrental.domain.Car;
import com.agodgrab.carrental.dto.CarDto;
import com.agodgrab.carrental.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {

    @Autowired
    RentMapper rentMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    CategoryService categoryService;

    public CarDto mapToCarDto(Car car) {
        return new CarDto.CarDtoBuilder()
                .id(car.getId())
                .model(car.getModel())
                .productionYear(car.getProductionYear())
                .vehicleMileage(car.getVehicleMileage())
                .doorQuantity(car.getDoorQuantity())
                .seatsQuantity(car.getSeatsQuantity())
                .categoryId(car.getCategory().getId())
                .listOfRents(rentMapper.mapToRentDtoList(car.getListOfRents()))
                .build();
    }

    public Car mapToCar(CarDto carDto) {
        return new Car(carDto.getId(), carDto.getModel(), carDto.getProductionYear(),
                carDto.getVehicleMileage(), carDto.getDoorQuantity(), carDto.getSeatsQuantity(),
                categoryService.findCategory(carDto.getCategoryId()), rentMapper.mapToRentList(carDto.getListOfRents()));
    }

    public List<CarDto> mapToCarDtoList(List<Car> cars) {
        return cars.stream()
                .map(car -> mapToCarDto(car))
                .collect(Collectors.toList());
    }

    public List<Car> mapToCarList(List<CarDto> carsDto) {
        return carsDto.stream()
                .map(carDto -> mapToCar(carDto))
                .collect(Collectors.toList());
    }
}
