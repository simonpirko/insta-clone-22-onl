package by.tms.instaclone22onl.services;

import by.tms.instaclone22onl.model.City;
import by.tms.instaclone22onl.storage.CityStorage;

import java.util.Optional;

public class CityService {
    private final CityStorage storage =  new CityStorage();
    public Optional<City> findById (int id){
        return storage.getId(id);
}
    public Optional<City> findByName(String name){
        return storage.getName(name);
    }
}
