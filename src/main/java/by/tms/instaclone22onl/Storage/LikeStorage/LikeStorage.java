package by.tms.instaclone22onl.Storage.LikeStorage;

import by.tms.instaclone22onl.model.Like;
import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;

import java.util.List;
import java.util.Optional;

public interface LikeStorage {
    boolean add(Like like);
    List <Like> getByUserId(int userId);
    List <Like> getByPostId(int postId);
    List<Like> getAll();
    Optional<Like> getByUserIdPostId(int userId, int postId);
    boolean deleteByUserIdPostId(int userId, int postId);

    boolean deleteByUserId(int userId);
    boolean deleteByPostId(int postId);
}

