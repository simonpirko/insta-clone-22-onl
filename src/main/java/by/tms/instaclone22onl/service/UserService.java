package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.storage.UserStorage.JdbcUserStorage;
import by.tms.instaclone22onl.storage.UserStorage.UserStorage;

import java.util.Optional;

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
}
