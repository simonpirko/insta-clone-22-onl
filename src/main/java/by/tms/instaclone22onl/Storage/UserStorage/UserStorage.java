package by.tms.instaclone22onl.storage.UserStorage;

/*
    @author Ilya Moiseenko on 20.09.23
*/

import by.tms.instaclone22onl.model.User;

import java.util.Optional;

public interface UserStorage {

    void add(User user);
    Optional<User> getById(int id);
    Optional<User> getByUsername(String username);
}
