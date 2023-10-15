package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.storage.UserStorage.JdbcUserStorage;
import by.tms.instaclone22onl.storage.UserStorage.UserStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.postgresql.jdbc.EscapedFunctions.INSERT;

public class UserService {
    private static UserService instance;
    private final UserStorage userStorage = JdbcUserStorage.getInstance();

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private UserService() {

    }

    public void add(User user) {
        userStorage.add(user);
    }

    public Optional<User> getUserById(int id) {
        return userStorage.getById(id);
    }

    public Optional<User> getUserByName(String username) {
        return userStorage.getByUsername(username);
    }

    public List<User> getUsersWithUsernameContaining(String keyword) {
        return userStorage.getUsersWithUsernameContaining(keyword);
    }

    public void follow(User follower, User followee) {
        follower.getFollowers().add(followee);
    }
    void add (User  follower,User followee) {
        try (Connection connection = JdbcConnection.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);

            preparedStatement.setInt(1, follower.getId());
            preparedStatement.setInt(2, followee.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void unfollow(User follower,User followee){
      follower.getFollowers().remove(followee);}

    void delete (User  follower,User followee) {
        try (Connection connection = JdbcConnection.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("delete * from \"followers\"(follower;followee)values(?,?)");

            preparedStatement.setInt(1, follower.getId());
            preparedStatement.setInt(2, followee.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
