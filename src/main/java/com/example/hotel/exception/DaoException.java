package com.example.hotel.exception;

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
