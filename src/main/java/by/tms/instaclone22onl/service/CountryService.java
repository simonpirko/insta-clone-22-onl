package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.entity.Country;
import by.tms.instaclone22onl.dao.CountryDao.*;
import by.tms.instaclone22onl.dao.UserDao.JdbcUserDao;
import by.tms.instaclone22onl.dao.UserDao.UserDao;
import lombok.Getter;


import java.util.List;
import java.util.Optional;

public class CountryService {

    private static CountryService instance;
    private final CountryDao storage = JdbcCountryDao.getInstance();
    @Getter
    private final UserDao userDao = JdbcUserDao.getInstance();

    public static CountryService getInstance() {
        if (instance == null) {
            instance = new CountryService();
        }
        return instance;
    }

    private CountryService() {
    }

    public Optional<Country> getById(int id) {
        return storage.getById(id);
    }

    public Optional<Country> getByName(String name) {
        return storage.getByName(name);
    }

    public List<Country> getAll() {
        return storage.getAll();
    }
}
