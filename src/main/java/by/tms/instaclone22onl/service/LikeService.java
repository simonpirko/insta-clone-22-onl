package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.entity.Like;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.dao.LikeDao.JdbcLikeDao;
import by.tms.instaclone22onl.dao.LikeDao.LikeDao;

import java.util.List;
import java.util.Optional;

/*
    @author Ilya Moiseenko on 2.10.23
*/

public class LikeService {

    private static LikeService instance;

    private final LikeDao likeDao = JdbcLikeDao.getInstance();

    private LikeService() {}

    public static LikeService getInstance() {
        if (instance == null)
            return new LikeService();

        return instance;
    }

    public void save(Like like) {
        likeDao.add(like);
    }

    public List<Like> findAllByPost(Post post) {
        return likeDao.getByPost(post);
    }

    public Optional<Like> findByUserAndPost(User user, Post post) {
        return likeDao.getByUserPost(user, post);
    }

    public void removeByUserAndPost(User user, Post post) {
        likeDao.deleteByUserPost(user, post);
    }
}
