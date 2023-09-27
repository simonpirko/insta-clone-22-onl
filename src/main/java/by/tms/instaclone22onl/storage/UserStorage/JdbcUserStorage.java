package by.tms.instaclone22onl.storage.UserStorage;

/*
    @author Ilya Moiseenko on 20.09.23
*/

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.model.Country;
import by.tms.instaclone22onl.model.User;

import java.sql.*;
import java.util.*;

public class JdbcUserStorage implements UserStorage {

    private static JdbcUserStorage instance;

    private final String INSERT = "insert into \"human\" (name, surname, username, photo, email, password, countryId) values (?, ?, ?, ?, ?, ?, ?)";
    private final String GET_BY_ID_WITH_COUNTRY = "select * from \"human\" join \"country\" on \"human\".country_id = \"country\".id where \"human\".id = ?";
    private final String GET_BY_USERNAME_WITH_COUNTRY = "select * from \"human\" join \"country\" on \"human\".country_id = \"country\".id where \"human\".username = ?";
    private final String UPDATE_USER_DATA = "UPDATE \"human\" SET name = ?, surname = ?, username = ?, photo = ?, email = ?, password = ?\n" +
            "WHERE id = ?";
    private final String UPDATE_USER_COUNTRY = "UPDATE \"country\" SET name = ? WHERE user_id = ?";
    private final String GET_BY_USERNAME_CONTAINING = "select * from \"human\" h join \"country\" c on h.country_id = c.id where lower (h.username) like lower (?)" +
            " order by username";

    private JdbcUserStorage() {}

    public static JdbcUserStorage getInstance() {
        if (instance == null)
            instance = new JdbcUserStorage();

        return instance;
    }

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
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_WITH_COUNTRY);
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

                Country country = new Country(
                        resultSet.getInt(9),
                        resultSet.getString(10)
                );

                user.setCountry(country);

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
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USERNAME_WITH_COUNTRY);
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

                Country country = new Country(
                        resultSet.getInt(9),
                        resultSet.getString(10)
                );

                user.setCountry(country);

                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public void updateById(User user) {
        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_DATA)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, Arrays.toString(Base64.getDecoder().decode(user.getPhoto())));
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setInt(7, user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUserCountry(int userId, String country) {
        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_COUNTRY)) {

            preparedStatement.setString(1, country);
            preparedStatement.setInt(2, userId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
   public List<User> getUsersWithUsernameContaining(String keyword){
        List<User> usersList = new ArrayList<>();

        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USERNAME_CONTAINING);

            preparedStatement.setString(1, keyword + "%");        //preparedStatement.setString(1, "%" + keyword + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                User user = new User();

                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setUsername(resultSet.getString(4));

                byte[] image = resultSet.getBytes(5);
                if(image != null) {
                user.setPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(5)));
                }

                user.setEmail(resultSet.getString(6));
                user.setPassword(resultSet.getString(7));

                Country country = new Country(
                        resultSet.getInt(9),
                        resultSet.getString(10)
                );

                user.setCountry(country);

                usersList.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usersList;

    }
}
