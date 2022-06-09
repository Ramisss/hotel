package com.example.hotel.service;

import com.example.hotel.dao.UserRepository;
import com.example.hotel.entity.User;

import java.util.List;

public class UserService {

    private final UserRepository userRepository = UserRepository.getInstance();



    List<User> findAll(){
        return userRepository.findAll().stream()
                .map()
    }


}
