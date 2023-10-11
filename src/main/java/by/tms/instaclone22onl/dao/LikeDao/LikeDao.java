package by.tms.instaclone22onl.dao.LikeDao;

import by.tms.instaclone22onl.entity.Like;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;

import java.util.List;
import java.util.Optional;

public interface LikeDao {
    boolean add(Like like);
    List <Like> getByUser(User user);
    List <Like> getByPost(Post post);
    List<Like> getAll();
    Optional<Like> getByUserPost(User user, Post post);
    boolean deleteByUserPost(User user, Post post);

    boolean deleteByUser(User user);
    boolean deleteByPost(Post post);
}

