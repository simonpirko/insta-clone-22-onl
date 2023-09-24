package by.tms.instaclone22onl.storage.CityStorage;

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.model.City;
import by.tms.instaclone22onl.model.Country;

import java.sql.*;
import java.util.Optional;

public class JdbcCityStorage implements CityStorage {
    private static JdbcCityStorage instance;
    private final String GET_BY_ID_SQL_SCRIPT = "SELECT * FROM \"City\" JOIN \"Country\"\n" +
                                                "on \"Country\".id = \"City\".countryid\n" +
                                                "WHERE \"City\".id = ?";
    private final String GET_BY_NAME_SQL_SCRIPT = "SELECT * FROM \"City\" JOIN \"Country\"\n" +
                                                  "on \"Country\".id = \"City\".countryid\n" +
                                                  "WHERE \"City\".name = ?;";
    private JdbcCityStorage() {
    }
    public static JdbcCityStorage getInstance(){
        if (instance == null){
            instance = new JdbcCityStorage();
        }
        return instance;
    }

    @Override
    public Optional<City> getById(int id) {
        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_SQL_SCRIPT)){

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){

                City city = new City(id);
                city.setId(resultSet.getInt(1));
                city.setName(resultSet.getString(2));

                Country country = new Country(
                        resultSet.getInt(4),
                        resultSet.getString(5));

                city.setCountry(country);

                return Optional.of(city);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<City> getByName(String name) {
        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME_SQL_SCRIPT)){

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){

                City city = new City(name);
                city.setId(resultSet.getInt(1));
                city.setName(resultSet.getString(2));

                Country country = new Country(
                        resultSet.getInt(4),
                        resultSet.getString(5));

                city.setCountry(country);

                return Optional.of(city);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}