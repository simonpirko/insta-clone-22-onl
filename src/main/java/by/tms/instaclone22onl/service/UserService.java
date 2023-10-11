package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.dao.UserDao.JdbcUserDao;
import by.tms.instaclone22onl.dao.UserDao.UserDao;

import java.util.List;
import java.util.Optional;

public class UserService {
    private static UserService instance;
    private final UserDao userDao = JdbcUserDao.getInstance();

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private UserService() {

    }

    public void add(User user) {
        userDao.add(user);
    }

    public Optional<User> getUserById(int id) {
        return userDao.getById(id);
    }

    public Optional<User> getUserByName(String username) {
        return userDao.getByUsername(username);
    }

    public List<User> getUsersWithUsernameContaining(String keyword){ return userDao.getUsersWithUsernameContaining(keyword); }
}
