package com.agodgrab.carrental.service;

import com.agodgrab.carrental.domain.User;
import com.agodgrab.carrental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final String ADMIN_LOGIN = "admin";

    public User getAdmin() {
        return userRepository.findByLogin(ADMIN_LOGIN).orElseThrow(NoSuchElementException::new);
    }

    public User findUser(long id) {

        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

}
