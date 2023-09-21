package by.tms.instaclone22onl.storage.CityStorage;

import by.tms.instaclone22onl.model.City;
import java.sql.*;
import java.util.Optional;

public class JdbcCityStorage implements CityStorage {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String userName = "postgres";
    private final String password = "root";
    private final String gitIdSqlScript = "SELECT name FROM \"postgres\".public.\"City\" WHERE id = ?";
    private final String getNameSqlScript = "SELECT id FROM \"postgres\".public.\"City\" WHERE name = ?";

    @Override
    public Optional<City> getId(int id) {
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(gitIdSqlScript)){

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                String rasName = resultSet.getString("name");

                City city = new City(id);
                city.setName(rasName);

                return Optional.of(city);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<City> getName(String name) {
        try (Connection connection = DriverManager.getConnection(url,  userName,  password);
             PreparedStatement preparedStatement = connection.prepareStatement(getNameSqlScript)){

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt("id");
                City city = new City(name);
                city.setId(id);

                return Optional.of(city);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
