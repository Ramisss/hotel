package com.example.hotel.dao;

import com.example.hotel.entity.User;
import com.example.hotel.entity.enums.Role;
import com.example.hotel.util.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements Dao<Integer, User> {

    private static final UserRepository INSTANCE = new UserRepository();

    private UserRepository() {

    }


    private static final String FIND_ALL = " select * from users; ";


    @Override
    public List<User> findAll() {
        try (var connection = ConnectionManager.open();
             PreparedStatement prepareStatement = connection.prepareStatement(FIND_ALL);

        ) {
            ResultSet resultSet = prepareStatement.executeQuery();

            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                userList.add(buildUser(resultSet));
            }

            return userList;

        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }

    }


    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public User save(User entity) {
        return null;
    }


    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("firstName", String.class),
                resultSet.getObject("lastName", String.class),
                resultSet.getObject("password", String.class),
                resultSet.getObject("phoneNumber", String.class),
                resultSet.getObject("email", String.class),
                resultSet.getObject("login", String.class),
                Role.valueOf(resultSet.getObject("roleId", String.class)));
    }

    public static UserRepository getInstance() {
        return INSTANCE;
    }
}
