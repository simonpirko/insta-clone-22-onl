package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.entity.Like;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.dao.LikeDao.JdbcLikeDao;
import by.tms.instaclone22onl.dao.LikeDao.LikeDao;

import java.util.Optional;

/*
    @author Ilya Moiseenko on 2.10.23
*/

public class LikeService {

    // Fields
    private static LikeService instance;

    private final LikeDao<Integer> likeDao = JdbcLikeDao.getInstance();

    // Constructors
    private LikeService() {}

    // Methods
    public static LikeService getInstance() {
        if (instance == null)
            instance = new LikeService();

        return instance;
    }

    public Optional<Integer> save(Like like) {
        return likeDao.save(like);
    }

    public Optional<Like> findByUserAndPost(User user, Post post) {
        return likeDao.findByUserAndPost(user, post);
    }

    public void removeByUserAndPost(User user, Post post) {
        likeDao.removeByUserAndPost(user, post);
    }

    public int findAllByPost(Post post) {
        return likeDao.findAllByPost(post);
    }
}
