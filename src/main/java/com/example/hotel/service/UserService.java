package com.example.hotel.service;

import com.example.hotel.dao.UserDaoImpl;
import com.example.hotel.entity.User;

import java.util.List;

public class UserService {

    private final UserDaoImpl userDaoImpl = UserDaoImpl.getInstance();


    List<User> findAll() {
        return null;
//        return userRepository.findAll().stream()
//                .map()
    }


}



