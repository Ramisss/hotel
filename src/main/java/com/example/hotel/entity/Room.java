package com.example.hotel.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Room {
    private Integer id;
    private Integer userId;
    private String name;
    private Short maxPerson;
    private Boolean lowSeasonRate;
    private Boolean highSessionRate;
    private Integer hotelId;

}
