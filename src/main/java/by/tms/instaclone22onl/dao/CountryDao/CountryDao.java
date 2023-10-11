package by.tms.instaclone22onl.dao.CountryDao;

import by.tms.instaclone22onl.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryDao {
    Optional<Country> getById(int id);
    Optional <Country> getByName (String name);
    List<Country> getAll ();
}
