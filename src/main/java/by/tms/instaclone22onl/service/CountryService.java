package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.entity.Country;
import by.tms.instaclone22onl.dao.CountryDao.*;

import java.util.Optional;

public class CountryService {

    // Fields
    private static CountryService instance;

    private final CountryDao<Integer> countryDao = JdbcCountryDao.getInstance();

    // Constructors
    private CountryService() {}

    // Methods
    public static CountryService getInstance() {
        if (instance == null) {
            instance = new CountryService();
        }

        return instance;
    }

    public Optional<Country> findById(Integer id) {
        return countryDao.findById(id);
    }

    public Optional<Country> findByName(String name) {
        return countryDao.findByName(name);
    }

    public Iterable<Country> getAll() {
        return countryDao.findAll();
    }
}
