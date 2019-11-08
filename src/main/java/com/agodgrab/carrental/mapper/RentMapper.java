package com.agodgrab.carrental.mapper;

import com.agodgrab.carrental.domain.Rent;
import com.agodgrab.carrental.dto.RentDto;
import com.agodgrab.carrental.service.CarService;
import com.agodgrab.carrental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentMapper implements Mapper<RentDto, Rent>{

    @Autowired
    UserService userService;

    @Autowired
    CarService carService;

    @Autowired
    PenaltyMapper penaltyMapper;

    @Override
    public RentDto mapToDto(Rent rent) {
        return new RentDto.RentDtoBuilder()
                .id(rent.getId())
                .userId(rent.getUser().getId())
                .carRentedId(rent.getCarRented().getId())
                .startDay(rent.getStartDay())
                .endDay(rent.getEndDay())
                .fuelLevel(rent.getFuelLevel())
                .withInsurance(rent.isWithInsurance())
                .withExtraCarTrunk(rent.isWithExtraCarTrunk())
                .penalties(penaltyMapper.mapToDtoList(rent.getListOfPenalties()))
                .toBePaid(rent.getToBePaid())
                .status(rent.getStatus())
                .build();
    }

    @Override
    public Rent mapToEntity(RentDto rentDto) {

        return new Rent(rentDto.getId(),
                userService.findUser(rentDto.getUserId()),
                carService.findCar(rentDto.getCarRentedId()),
                rentDto.getStartDay(),
                rentDto.getEndDay(),
                rentDto.getFuelLevel(),
                rentDto.isWithInsurance(),
                rentDto.isWithExtraCarTrunk(),
                penaltyMapper.mapToEntitiesList(rentDto.getListOfPenalties()),
                rentDto.getToBePaid(),
                rentDto.getStatus());

    }

}
