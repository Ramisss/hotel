package com.example.hotel.exeption;

public class DaoException extends Exception {


    public DaoException() {
    }

    public DaoException(String msg){
        super(msg);
    }

    public DaoException(String msg, Throwable cause){
        super(msg,cause);
    }
}
