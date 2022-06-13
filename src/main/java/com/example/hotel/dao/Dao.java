package com.example.hotel.dao;

import com.example.hotel.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {

    List<T> findAll() throws DaoException;

    Optional<T> findById(K id) throws DaoException, SQLException;

    boolean delete(K id) throws DaoException ;

    void update(T entity) throws DaoException;

    T save(T entity) throws DaoException;


}
