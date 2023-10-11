package by.tms.instaclone22onl.dao.CityDao;

import by.tms.instaclone22onl.entity.City;
import java.util.Optional;

public interface CityDao {
    Optional <City> getById(int id);
    Optional <City> getByName(String name);
}