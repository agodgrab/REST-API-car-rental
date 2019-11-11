package com.agodgrab.carrental.controller;

import com.agodgrab.carrental.domain.Car;
import com.agodgrab.carrental.dto.CarDto;
import com.agodgrab.carrental.mapper.CarMapper;
import com.agodgrab.carrental.service.CarService;
import com.agodgrab.carrental.service.LogHistoryService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvcHttpRequestsMaker;
    @MockBean
    private CarService carService;
    @MockBean
    private CarMapper carMapper;

    @Test
    public void shouldSearchEmptyList() throws Exception {
        //GIVEN
        List<CarDto> carDtos = new ArrayList<>();
        List<Car> cars = new ArrayList<>();
        LocalDate start = LocalDate.of(2019, 9, 25);
        LocalDate end = LocalDate.of(2019, 9, 28);
        when(carService.searchCar(start, end, "family")).thenReturn(cars);
        when(carMapper.mapToDtoList(cars)).thenReturn(carDtos);
        //WHEN & THEN
        mockMvcHttpRequestsMaker.perform(get("/v1/cars")
                .param("start", "2019-09-25")
                .param("end", "2019-09-28")
                .param("category", "family")
                .with(user("user").password("userPass").roles("USER"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldSearchCarList() throws Exception {
        //GIVEN
        List<CarDto> carDtos = new ArrayList<>();
        carDtos.add(new CarDto.CarDtoBuilder().id(1L).model("test_model").build());
        List<Car> cars = new ArrayList<>();
        cars.add(new Car());
        LocalDate start = LocalDate.of(2019, 9, 25);
        LocalDate end = LocalDate.of(2019, 9, 28);
        when(carService.searchCar(start, end, "family")).thenReturn(cars);
        when(carMapper.mapToDtoList(cars)).thenReturn(carDtos);
        //WHEN & THEN
        mockMvcHttpRequestsMaker.perform(get("/v1/cars")
                .param("start", "2019-09-25")
                .param("end", "2019-09-28")
                .param("category", "family")
                .with(user("user").password("userPass").roles("USER"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].model", is("test_model")));
    }

    @Test
    public void shouldAddNewCar() throws Exception {
        //GIVEN
        CarDto carDto = new CarDto.CarDtoBuilder().id(1L).model("test_model").build();
        Car car = new Car();
        when(carMapper.mapToEntity(carDto)).thenReturn(car);
        when(carService.addCar(org.mockito.Matchers.any(Car.class))).thenReturn(car);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);
        //WHEN & THEN
        mockMvcHttpRequestsMaker.perform(post("/v1/car")
                .with(user("admin").password("adminPass").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));

    }

    @Test
    public void shouldUpdateCar() throws Exception {
        //GIVEN
        CarDto carDto = new CarDto.CarDtoBuilder().id(1L).model("test_update").build();
        Car car = new Car();
        when(carMapper.mapToEntity(carDto)).thenReturn(car);
        when(carService.updateCar(org.mockito.Matchers.any(Car.class))).thenReturn(car);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);
        //WHEN & THEN
        mockMvcHttpRequestsMaker.perform(put("/v1/car")
                .with(user("admin").password("adminPass").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));

    }


    @Test
    public void shouldDeleteCar() throws Exception{
        //GIVEN
        //WHEN & THEN
        mockMvcHttpRequestsMaker.perform(delete("/v1/car")
                .param("id", "1")
                .with(user("admin").password("adminPass").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

}