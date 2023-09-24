package by.tms.instaclone22onl.storage.LikeStorage;

import by.tms.instaclone22onl.model.Like;
import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;

import java.util.List;
import java.util.Optional;

public interface LikeStorage {
    boolean add(Like like);
    List <Like> getByUser(User user);
    List <Like> getByPost(Post post);
    List<Like> getAll();
    Optional<Like> getByUserPost(User user, Post post);
    boolean deleteByUserPost(User user, Post post);

    boolean deleteByUser(User user);
    boolean deleteByPost(Post post);
}

