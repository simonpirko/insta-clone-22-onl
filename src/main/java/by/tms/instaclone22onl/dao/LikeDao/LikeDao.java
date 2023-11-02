package by.tms.instaclone22onl.dao.LikeDao;

import by.tms.instaclone22onl.entity.Like;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.Story;
import by.tms.instaclone22onl.entity.User;

import java.util.Optional;

public interface LikeDao<ID> {

    Optional<ID> save(Like like);
    Iterable<Like> findAll();
    Optional<Like> findByUserAndPost(User user, Post post);
    Optional<Like> findByUserAndStory(User user, Story story);
    void removeByUserAndPost(User user, Post post);
    void removeByUserAndStory(User user, Story story);
    int findAllByPost(Post post);
    int findAllByStory(Story story);
}

