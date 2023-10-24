package by.tms.instaclone22onl.service;

/*
    @author Ilya Moiseenko on 24.10.23
*/

import by.tms.instaclone22onl.dao.FavoriteDao.FavoriteDao;
import by.tms.instaclone22onl.dao.FavoriteDao.JdbcFavoriteDao;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;

import java.util.Optional;

public class FavoriteService {

    // Fields
    private static FavoriteService instance;

    private final FavoriteDao<Integer> favoriteDao = JdbcFavoriteDao.getInstance();

    // Constructors
    private FavoriteService() {}

    // Methods
    public static FavoriteService getInstance() {
        if (instance == null)
            instance = new FavoriteService();

        return instance;
    }

    public Optional<Integer> save(User user, Post post) {
        return favoriteDao.save(user, post);
    }
}
