package by.tms.instaclone22onl.dao.FavoriteDao;

/*
    @author Ilya Moiseenko on 24.10.23
*/

import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;

import java.util.Optional;

public interface FavoriteDao<ID> {

    Optional<ID> save(User user, Post post);
}
