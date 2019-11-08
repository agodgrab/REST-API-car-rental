package com.agodgrab.carrental.mapper;

import com.agodgrab.carrental.domain.User;
import com.agodgrab.carrental.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserDto, User> {

    @Override
    public UserDto mapToDto(User user) {
        return new UserDto(user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getMail(),
                user.getFirstname(),
                user.getLastname(),
                user.getRole());
    }

    @Override
    public User mapToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setMail(userDto.getMail());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setRole(userDto.getRole());
        return user;
    }
}
