package com.example.hotel.dao;

import com.example.hotel.entity.User;
import com.example.hotel.entity.enums.Role;
import com.example.hotel.util.ConnectionManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements Dao<Integer, User> {

    static Logger logger = LogManager.getLogger();

    private static final UserRepository INSTANCE = new UserRepository();

    private UserRepository() {

    }

    public static UserRepository getInstance() {
        return INSTANCE;
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
            logger.log(Level.ERROR,"SQL Exception findAll method USERDAO");
            throw new RuntimeException(sqlException);

        }

    }


    @Override
    public Optional<User> findById(Integer id) throws SQLException { // TODO ==> IS IT CORRECT RETURN Optional ??
        String sql = """
                select *
                from users
                where id = ?;
                """;

        Connection open = ConnectionManager.open();
        ResultSet resultSet;
        try (PreparedStatement prepareStatement = open.prepareStatement(sql)) {
            prepareStatement.setInt(1, id);
            resultSet = prepareStatement.executeQuery();
        }
        User user = null;

        while (resultSet.next()) {
            user = buildUser(resultSet);
        }

        return Optional.of(user); // TODO Optional.of() is it CORRECT?
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
                resultSet.getObject("first_name", String.class),
                resultSet.getObject("last_name", String.class),
                resultSet.getObject("password", String.class),
                resultSet.getObject("phone_number", String.class),
                resultSet.getObject("e_mail", String.class),// TODO email or e_mail ??
                resultSet.getObject("login", String.class),
                Role.valueOf(resultSet.getObject("role_id", String.class)));
    }

    public User findByLogin(String login) throws SQLException {
        String sql = """
                select *
                from users
                where login = ?;
                """;
        User user = null;
        try (Connection open = ConnectionManager.open()) {
            PreparedStatement prepareStatement = open.prepareStatement(sql);
            int i = 7;
            prepareStatement.setString(i, login);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                user = buildUser(resultSet);
            }
        }

        return user;
    }
}
