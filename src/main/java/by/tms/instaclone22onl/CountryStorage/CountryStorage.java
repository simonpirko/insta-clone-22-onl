package by.tms.instaclone22onl.CountryStorage;

import by.tms.instaclone22onl.model.Country;

import java.util.Optional;

public interface CountryStorage {
    Optional<Country> getById(int id);
    Optional <Country> getByName (String name);
}
