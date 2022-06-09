package com.example.hotel.entity.enums;

public enum Role {
    ROLE_ADMIN(1),
    ROLE_USER(2);

    private int id;

    Role(int id) {
        this.id = id;
    }


}
