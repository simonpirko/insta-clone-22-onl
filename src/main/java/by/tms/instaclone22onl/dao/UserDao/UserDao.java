package by.tms.instaclone22onl.dao.UserDao;

/*
    @author Ilya Moiseenko on 20.09.23
*/

import by.tms.instaclone22onl.entity.User;

import java.util.Optional;

public interface UserDao<ID> {

    Optional<ID> save(User user);
    Optional<User> findById(ID id);
    Optional<User> findByUsername(String username);
    Iterable<User> findUsersWithUsernameContaining(String username);
    void update(User user);
}
