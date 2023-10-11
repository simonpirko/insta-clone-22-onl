package by.tms.instaclone22onl.dao.LikeDao;

import by.tms.instaclone22onl.entity.Like;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;

import java.util.Optional;

public interface LikeDao<ID> {

    Optional<ID> save(Like like);
    Iterable<Like> findAll();
    Optional<Like> findByUserAndPost(User user, Post post);
    void removeByUserAndPost(User user, Post post);
    int findAllByPost(Post post);
}

