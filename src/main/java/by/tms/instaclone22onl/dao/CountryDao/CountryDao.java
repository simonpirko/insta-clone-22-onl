package by.tms.instaclone22onl.dao.CountryDao;

import by.tms.instaclone22onl.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryDao<ID> {

    Optional<Country> findById(ID id);
    Optional <Country> findByName (String name);
    Iterable<Country> findAll ();
}
