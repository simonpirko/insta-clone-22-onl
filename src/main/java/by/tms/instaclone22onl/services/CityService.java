package by.tms.instaclone22onl.services;

import by.tms.instaclone22onl.model.City;
import by.tms.instaclone22onl.storage.CityStorage;
import by.tms.instaclone22onl.storage.JdbcCityStorage;

import java.util.Optional;

public class CityService {
    private static CityService instance;
    private final CityStorage storage = JdbcCityStorage.getInstance();

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
