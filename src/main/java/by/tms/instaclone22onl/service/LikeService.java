package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.model.Like;
import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.storage.LikeStorage.JdbcLikeStorage;
import by.tms.instaclone22onl.storage.LikeStorage.LikeStorage;

import java.util.List;
import java.util.Optional;

/*
    @author Ilya Moiseenko on 2.10.23
*/

public class LikeService {

    private static LikeService instance;

    private final LikeStorage likeStorage = JdbcLikeStorage.getInstance();

    private LikeService() {}

    public static LikeService getInstance() {
        if (instance == null)
            return new LikeService();

        return instance;
    }

    public void save(Like like) {
        likeStorage.add(like);
    }

    public List<Like> findAllByPost(Post post) {
        return likeStorage.getByPost(post);
    }

    public Optional<Like> findByUserAndPost(User user, Post post) {
        return likeStorage.getByUserPost(user, post);
    }

    public void removeByUserAndPost(User user, Post post) {
        likeStorage.deleteByUserPost(user, post);
    }
}
