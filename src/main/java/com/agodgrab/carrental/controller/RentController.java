package com.agodgrab.carrental.controller;

import com.agodgrab.carrental.mapper.RentMapper;
import com.agodgrab.carrental.mapper.UserMapper;
import com.agodgrab.carrental.dto.RentDto;
import com.agodgrab.carrental.dto.UserDto;
import com.agodgrab.carrental.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/rent")
public class RentController {

    @Autowired
    private RentService rentService;

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private UserMapper userMapper;

    @PostMapping(value = "book", consumes = APPLICATION_JSON_VALUE)
    public void booking(@RequestBody RentDto rentDto) {
        rentService.makeReservation(rentMapper.mapToRent(rentDto));

    }

    @PutMapping(value = "cancel", consumes = APPLICATION_JSON_VALUE)
    public void cancellingBook(@RequestBody RentDto rentDto) {
        rentService.cancelReservation(rentMapper.mapToRent(rentDto));

    }

    @PutMapping(value = "confirm", consumes = APPLICATION_JSON_VALUE)
    public void rent(@RequestBody RentDto rentDto) {
        rentService.confirmRent(rentMapper.mapToRent(rentDto));

    }

    @PutMapping(value = "return", consumes = APPLICATION_JSON_VALUE)
    public void returnCar(@RequestBody RentDto rentDto) {
        rentService.confirmReturn(rentMapper.mapToRent(rentDto));

    }

    @GetMapping(value = "history", consumes = APPLICATION_JSON_VALUE)
    public List<RentDto> getRents(@RequestBody UserDto userDto) {
        return rentMapper.mapToRentDtoList(rentService.fetchUserRents(userMapper.mapToUser(userDto)));
    }

}
