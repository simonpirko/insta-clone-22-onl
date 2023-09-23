package by.tms.instaclone22onl.CountryStorage;

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.model.Country;

import java.sql.*;
import java.util.Optional;

public class JdbcCountryStorage implements CountryStorage{

    private static JdbcCountryStorage instance;

    private final String GET_BY_ID = "select * from \"Country\" where id = ?";
    private final String GET_BY_COUNTRY_NAME = "select * from \"Country\" where name = ?";

    private JdbcCountryStorage() {}

    public static JdbcCountryStorage getInstance() {
        if (instance == null)
            instance = new JdbcCountryStorage();

        return instance;
    }

    @Override
    public Optional<Country> getById(int id) {
        try {Connection connection = JdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Country country = new Country();

                country.setId(resultSet.getInt(1));
                country.setName(resultSet.getString(2));

                return Optional.of(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


            @Override
            public Optional<Country> getByName (String name){
                try {Connection connection = JdbcConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_COUNTRY_NAME);
                    preparedStatement.setString(1, name);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        Country country = new Country();

                        country.setId(resultSet.getInt(1));
                        country.setName(resultSet.getString(2));

                        return Optional.of(country);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return Optional.empty();
            }
        }
