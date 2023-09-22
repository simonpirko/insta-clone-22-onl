package by.tms.instaclone22onl.storage.CommentStorage;

import by.tms.instaclone22onl.model.Comment;
import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;

import java.util.Optional;

public interface CommentStorage {

    void add(Comment comment);

    Optional<Comment> getByPost(Post post);

    Optional<Comment> getByUser(User user);
}
