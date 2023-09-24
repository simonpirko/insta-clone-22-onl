package by.tms.instaclone22onl.storage;

import by.tms.instaclone22onl.model.City;
import java.util.Optional;

public interface CityStorage {
    Optional <City> getById(int id);
    Optional <City> getByName(String name);
}