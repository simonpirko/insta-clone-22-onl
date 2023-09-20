package by.tms.instaclone22onl.storage;

/*
    @author Ilya Moiseenko on 20.09.23
*/

import by.tms.instaclone22onl.model.User;

import java.util.Optional;

public interface UserStorage {

    boolean add(User user);
    Optional<User> get(int id);
    Optional<User> get(String username);
}
