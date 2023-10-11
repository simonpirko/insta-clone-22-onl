package by.tms.instaclone22onl.dao.CityDao;

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.entity.City;
import by.tms.instaclone22onl.entity.Country;

import java.sql.*;
import java.util.Optional;

public class JdbcCityDao implements CityDao {
    private static JdbcCityDao instance;
    private final String GET_BY_ID_SQL_SCRIPT = "SELECT * FROM \"сity\" JOIN \"сountry\"\n" +
                                                "on \"сountry\".id = \"сity\".country_id\n" +
                                                "WHERE \"сity\".id = ?";
    private final String GET_BY_NAME_SQL_SCRIPT = "SELECT * FROM \"сity\" JOIN \"сountry\"\n" +
                                                  "on \"сountry\".id = \"сity\".country_id\n" +
                                                  "WHERE \"сity\".name = ?;";
    private JdbcCityDao() {
    }
    public static JdbcCityDao getInstance(){
        if (instance == null){
            instance = new JdbcCityDao();
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

                City city = City
                        .builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .build();

                Country country = Country
                        .builder()
                        .id(resultSet.getInt(4))
                        .name(resultSet.getString(5))
                        .build();

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

                City city = City
                        .builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .build();

                Country country = Country
                        .builder()
                        .id(resultSet.getInt(4))
                        .name(resultSet.getString(5))
                        .build();

                city.setCountry(country);

                return Optional.of(city);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}