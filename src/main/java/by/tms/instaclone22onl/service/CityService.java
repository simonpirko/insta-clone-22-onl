package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.entity.City;
import by.tms.instaclone22onl.dao.CityDao.CityDao;
import by.tms.instaclone22onl.dao.CityDao.JdbcCityDao;

import java.util.Optional;

public class CityService {
    private static CityService instance;
    private final CityDao storage = JdbcCityDao.getInstance();

    public static CityService getInstance(){
        if (instance == null){
            instance = new CityService();
        }
        return instance;
    }
    private CityService() {}

        public Optional<City> getCityById(int id) {
            return storage.getById(id);
        }

        public Optional<City> getCityByName(String name) {
            return storage.getByName(name);
        }
}
