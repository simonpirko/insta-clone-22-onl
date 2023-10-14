package by.tms.instaclone22onl.dao.CityDao;

import by.tms.instaclone22onl.entity.City;
import java.util.Optional;

public interface CityDao<ID> {
    Optional<City> findById(ID id);
    Optional<City> findByName(String name);
}