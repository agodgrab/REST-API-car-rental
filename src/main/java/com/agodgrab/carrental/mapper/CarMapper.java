package com.agodgrab.carrental.mapper;

import com.agodgrab.carrental.domain.Car;
import com.agodgrab.carrental.dto.CarDto;
import com.agodgrab.carrental.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarMapper implements Mapper<CarDto, Car>{

    @Autowired
    RentMapper rentMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    CategoryService categoryService;

    @Override
    public CarDto mapToDto(Car car) {
        return new CarDto.CarDtoBuilder()
                .id(car.getId())
                .model(car.getModel())
                .productionYear(car.getProductionYear())
                .vehicleMileage(car.getVehicleMileage())
                .doorQuantity(car.getDoorQuantity())
                .seatsQuantity(car.getSeatsQuantity())
                .categoryId(car.getCategory().getId())
                .listOfRents(rentMapper.mapToDtoList(car.getListOfRents()))
                .build();
    }

    @Override
    public Car mapToEntity(CarDto carDto) {
        return new Car(carDto.getId(), carDto.getModel(), carDto.getProductionYear(),
                carDto.getVehicleMileage(), carDto.getDoorQuantity(), carDto.getSeatsQuantity(),
                categoryService.findCategory(carDto.getCategoryId()), rentMapper.mapToEntitiesList(carDto.getListOfRents()));
    }
}
