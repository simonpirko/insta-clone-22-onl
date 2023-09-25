package by.tms.instaclone22onl.storage.CountryStorage;

import by.tms.instaclone22onl.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryStorage {
    Optional<Country> getById(int id);
    Optional <Country> getByName (String name);
    List<Country> getAll ();
}
