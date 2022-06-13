package com.example.hotel.dao;

import com.example.hotel.entity.User;
import com.example.hotel.entity.type.Role;
import com.example.hotel.exception.DaoException;
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

public class UserDaoImpl implements Dao<Integer, User> {

    static Logger logger = LogManager.getLogger();

    private static final UserDaoImpl INSTANCE = new UserDaoImpl();

    private UserDaoImpl() {

    }

    public static UserDaoImpl getInstance() {
        return INSTANCE;
    }


    private static final String FIND_ALL = " select users.id, first_name, last_name, password, " +
            "phone_number, e_mail, login, role_id" +
            " from users; ";


    String findById = """
            select first_name, last_name, password, phone_number, e_mail, login, role_id
            from users
            where id = ?;
            """;

    String findByLogin = """
            select users.id, first_name, last_name, password, phone_number, e_mail, login, role_id
            from users
            where login = ?;
            """;


    @Override
    public List<User> findAll() throws DaoException {
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
            logger.log(Level.ERROR, "SQL Exception findAll method USERDAO");
            throw new DaoException();

        }

    }


    @Override
    public Optional<User> findById(Integer id) throws SQLException { // TODO ==> IS IT CORRECT RETURN Optional ??
        Connection open = ConnectionManager.open();
        ResultSet resultSet;
        try (PreparedStatement prepareStatement = open.prepareStatement(findById)) {
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


    public User findByLogin(String login) throws SQLException {

        User user = null;
        try (Connection open = ConnectionManager.open()) {
            PreparedStatement prepareStatement = open.prepareStatement(findByLogin);
            prepareStatement.setString(1, login);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                user = buildUser(resultSet);
            }
        }

        return user;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("first_name", String.class),
                resultSet.getObject("last_name", String.class),
                resultSet.getObject("password", String.class),
                resultSet.getObject("phone_number", String.class),
                resultSet.getObject("e_mail", String.class),
                resultSet.getObject("login", String.class),
                Role.valueOf(resultSet.getObject("role_id", String.class)));
    }
}
