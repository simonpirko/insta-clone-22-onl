package by.tms.instaclone22onl.storage;

/*
    @author Ilya Moiseenko on 20.09.23
*/

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.model.Country;
import by.tms.instaclone22onl.model.User;

import java.sql.*;
import java.util.*;

public class JdbcUserStorage implements UserStorage {

    private final String INSERT = "insert into \"Human\" (name, surname, username, photo, email, password, countryId) values (?, ?, ?, ?, ?, ?, ?)";
    private final String GET_BY_ID = "select * from \"Human\" where id = ?";
    private final String GET_BY_USERNAME = "select * from \"Human\" where username = ?";

    @Override
    public void add(User user) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setBytes(4, Base64.getDecoder().decode(user.getPhoto()));
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setInt(7, user.getCountry().getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> getById(int id) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setUsername(resultSet.getString(4));
                user.setPhoto(
                        Base64.getEncoder().encodeToString(resultSet.getBytes(5))
                );
                user.setEmail(resultSet.getString(6));
                user.setPassword(resultSet.getString(7));
                user.setCountry(new Country());

                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> getByUsername(String username) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USERNAME);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setUsername(resultSet.getString(4));
                user.setPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(5)));
                user.setEmail(resultSet.getString(6));
                user.setPassword(resultSet.getString(7));
                user.setCountry(new Country());

                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
