package by.tms.instaclone22onl.storage;

import by.tms.instaclone22onl.model.City;
import java.util.Optional;

public interface Storage {
    Optional <City> getId(int id);
    Optional <City> getName(String name);
}