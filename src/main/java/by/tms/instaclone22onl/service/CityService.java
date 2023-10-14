package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.entity.City;
import by.tms.instaclone22onl.dao.CityDao.CityDao;
import by.tms.instaclone22onl.dao.CityDao.JdbcCityDao;

import java.util.Optional;

public class CityService {

    // Fields
    private static CityService instance;

    private final CityDao<Integer> cityDao = JdbcCityDao.getInstance();

    // Constructors
    private CityService() {}

    // Methods
    public static CityService getInstance() {
        if (instance == null) {
            instance = new CityService();
        }

        return instance;
    }

    public Optional<City> findCityById(Integer id) {
        return cityDao.findById(id);
    }

    public Optional<City> findCityByName(String name) {
        return cityDao.findByName(name);
    }
}
