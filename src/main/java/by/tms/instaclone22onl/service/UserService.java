package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.dao.UserDao.JdbcUserDao;
import by.tms.instaclone22onl.dao.UserDao.UserDao;

import java.util.Optional;

public class UserService {

    // Fields
    private static UserService instance;
    private final UserDao<Integer> userDao = JdbcUserDao.getInstance();

    // Constructors
    private UserService() {}

    // Methods
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }

        return instance;
    }

    public Optional<Integer> save(User user) {
        return userDao.save(user);
    }

    public Optional<User> findUserById(Integer id) {
        return userDao.findById(id);
    }

    public Optional<User> findUserByName(String username) {
        return userDao.findByUsername(username);
    }

    public Iterable<User> getUsersWithUsernameContaining(String keyword) {
        return userDao.findUsersWithUsernameContaining(keyword);
    }

    public void update(User user) {
        userDao.update(user);
    }
}
