package by.tms.instaclone22onl.storage.LikeStorage;

import by.tms.instaclone22onl.model.Like;
import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;

import java.util.List;
import java.util.Optional;

public interface LikeStorage {
    boolean addLike(Like like);
    Optional<Like> getLikeByUser(User user);
    Optional<Like> getLikeByPost(Post post);
    List<Like> getAllLike();
}

