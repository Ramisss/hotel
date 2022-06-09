package com.example.hotel.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Hotel {

    private Integer id;
    private String name;
    private String address;
    private String country;
    private String phoneNumber;
    private String email;


}
