package com.example.hotel.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@RequiredArgsConstructor
@Data
public class Orders {
    private Integer id;
    private Integer userId;
    private Integer roomId;
    private Date checkIn;
    private Date checkOut;
    private Double price;
    private Byte rate;
    private Date orderDate;
    


}
