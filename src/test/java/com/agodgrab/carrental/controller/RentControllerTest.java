package com.agodgrab.carrental.controller;

import com.agodgrab.carrental.domain.Rent;
import com.agodgrab.carrental.domain.User;
import com.agodgrab.carrental.domain.enums.UserRole;
import com.agodgrab.carrental.dto.RentDto;
import com.agodgrab.carrental.dto.UserDto;
import com.agodgrab.carrental.mapper.RentMapper;
import com.agodgrab.carrental.mapper.UserMapper;
import com.agodgrab.carrental.service.LogHistoryService;
import com.agodgrab.carrental.service.RentService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@WebMvcTest(RentController.class)
public class RentControllerTest {

    @Autowired
    private MockMvc mockMvcHttpRequestsMaker;
    @MockBean
    private RentService rentService;
    @MockBean
    private RentMapper rentMapper;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private LogHistoryService logHistoryService;

    @Test
    public void shouldBookRent() throws Exception {
        //GIVEN
        RentDto rentDto = new RentDto.RentDtoBuilder().id(1L).build();
        Rent rent = new Rent();
        when(rentMapper.mapToEntity(rentDto)).thenReturn(rent);
        when(rentService.makeReservation(rent)).thenReturn(rent);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(rentDto);
        //WHEN & THEN
        mockMvcHttpRequestsMaker.perform(post("/v1/rent/book")
                .with(user("user").password("userPass").roles("USER"))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldCancelBook() throws Exception {
        //GIVEN
        RentDto rentDto = new RentDto.RentDtoBuilder().id(1L).build();
        Rent rent = new Rent();
        when(rentMapper.mapToEntity(rentDto)).thenReturn(rent);
        when(rentService.cancelReservation(rent)).thenReturn(rent);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(rentDto);
        //WHEN & THEN
        mockMvcHttpRequestsMaker.perform(put("/v1/rent/cancel")
                .with(user("user").password("userPass").roles("USER"))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldConfirmRent() throws Exception {
        //GIVEN
        RentDto rentDto = new RentDto.RentDtoBuilder().id(1L).build();
        Rent rent = new Rent();
        when(rentMapper.mapToEntity(rentDto)).thenReturn(rent);
        when(rentService.confirmRent(rent)).thenReturn(rent);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(rentDto);
        //WHEN & THEN
        mockMvcHttpRequestsMaker.perform(put("/v1/rent/confirm")
                .with(user("admin").password("adminPass").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldReturnCar() throws Exception {
        //GIVEN
        RentDto rentDto = new RentDto.RentDtoBuilder().id(1L).build();
        Rent rent = new Rent();
        when(rentMapper.mapToEntity(rentDto)).thenReturn(rent);
        when(rentService.confirmReturn(rent)).thenReturn(rent);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(rentDto);
        //WHEN & THEN
        mockMvcHttpRequestsMaker.perform(put("/v1/rent/return")
                .with(user("admin").password("adminPass").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
    }


    @Test
    public void shouldFetchEmptyRentList() throws Exception {
        //GIVEN
        UserDto userDto = new UserDto(1L, "xxx", "xxx", "xxx", "xxx", "xxx", UserRole.USER);
        User user = new User(1L, "xxx", "xxx", "xxx", "xxx", "xxx", new ArrayList<>(), new ArrayList<>(), UserRole.USER);
        List<Rent> rentList = new ArrayList<>();
        List<RentDto> rentDtoList = new ArrayList<>();

        when(userMapper.mapToEntity(userDto)).thenReturn(user);
        when(rentService.fetchUserRents(user)).thenReturn(rentList);
        when(rentMapper.mapToDtoList(rentList)).thenReturn(rentDtoList);

        //verify(rentService).fetchUserRents(user);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);
        //WHEN & THEN
        mockMvcHttpRequestsMaker.perform(get("/v1/rent/history")
                .with(user("user").password("userPass").roles("USER"))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchRentList() throws Exception {
        //GIVEN
        UserDto userDto = new UserDto();
        List<RentDto> rentDtoList = new ArrayList<>();
        rentDtoList.add(new RentDto.RentDtoBuilder().id(23).build());
        when(rentMapper.mapToDtoList(Mockito.any())).thenReturn(rentDtoList);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);
        //WHEN & THEN
        mockMvcHttpRequestsMaker.perform(get("/v1/rent/history")
                .with(user("user").password("userPass").roles("USER"))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(23)));
    }
}

