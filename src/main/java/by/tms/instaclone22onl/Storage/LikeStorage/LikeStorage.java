package by.tms.instaclone22onl.Storage.LikeStorage;

import by.tms.instaclone22onl.model.Like;
import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;

import java.util.List;
import java.util.Optional;

public interface LikeStorage {
    boolean add(Like like);
    Optional<Like> getByUser(User user);
    Optional<Like> getByPost(Post post);
    List<Like> getAll();
    boolean deleteByUser(User user);
    boolean deleteByPost(Post post);
}

