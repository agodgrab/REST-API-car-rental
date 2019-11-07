package com.kodilla.kodilla.diplomaBackend.mapper;

import com.kodilla.kodilla.diplomaBackend.domain.Rent;
import com.kodilla.kodilla.diplomaBackend.dto.RentDto;
import com.kodilla.kodilla.diplomaBackend.service.CarService;
import com.kodilla.kodilla.diplomaBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentMapper {

    @Autowired
    UserService userService;

    @Autowired
    CarService carService;

    public Rent mapToRent(RentDto rentDto) {
        return new Rent(rentDto.getId(),
                userService.findUser(rentDto.getUserId()),
                carService.findCar(rentDto.getCarRentedId()),
                rentDto.getStartDay(),
                rentDto.getEndDay(),
                rentDto.getFuelLevel(),
                rentDto.isWithInsurance(),
                rentDto.isWithExtraCarTrunk(),
                rentDto.getListOfPenalties(),
                rentDto.getToBePaid(),
                rentDto.getStatus());
    }

    public RentDto mapToRentDto(Rent rent) {
        return new RentDto.RentDtoBuilder()
                .id(rent.getId())
                .userId(rent.getUser().getId())
                .carRentedId(rent.getCarRented().getId())
                .startDay(rent.getStartDay())
                .endDay(rent.getEndDay())
                .fuelLevel(rent.getFuelLevel())
                .withInsurance(rent.isWithInsurance())
                .withExtraCarTrunk(rent.isWithExtraCarTrunk())
                .penalties(rent.getListOfPenalties())
                .toBePaid(rent.getToBePaid())
                .status(rent.getStatus())
                .build();
    }

    public List<RentDto> mapToRentDtoList(final List<Rent> rentsList) {
        return rentsList.stream()
                .map(rent -> mapToRentDto(rent))
                .collect(Collectors.toList());
    }

    public List<Rent> mapToRentList(final List<RentDto> rentsDtoList) {
        return rentsDtoList.stream()
                .map(rentDto -> mapToRent(rentDto))
                .collect(Collectors.toList());
    }
}
