package com.kodilla.kodilla.diplomaBackend.Car.controller;

import com.kodilla.kodilla.diplomaBackend.Car.dto.CarDto;
import com.kodilla.kodilla.diplomaBackend.Car.mapper.CarMapper;
import com.kodilla.kodilla.diplomaBackend.Car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarMapper carMapper;

    @GetMapping(value="/cars")
    public List<CarDto> searchCars(@RequestParam(name = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam(name = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate endDate, @RequestParam(name = "category") String category){
        return carMapper.mapToCarDtoList(carService.searchCar(startDate, endDate, category));
    }

    @PostMapping(value="/car", consumes = APPLICATION_JSON_VALUE)
    public void addNewCar(@RequestBody CarDto carDto){
        carService.addCar(carMapper.mapToCar(carDto));
    }

    @DeleteMapping(value="/car")
    public void deleteCar(@RequestParam(name="id") Long id){
        carService.deleteCar(id);
    }

    @PutMapping(value="/car", consumes = APPLICATION_JSON_VALUE)
    public CarDto updateCarData(@RequestBody CarDto carDto){
        return carMapper.mapToCarDto(carService.updateCar(carMapper.mapToCar(carDto)));
    }


}
