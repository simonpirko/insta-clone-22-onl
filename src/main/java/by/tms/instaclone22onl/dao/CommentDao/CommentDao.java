package by.tms.instaclone22onl.dao.CommentDao;

import by.tms.instaclone22onl.entity.Comment;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;

import java.util.List;
import java.util.Optional;

public interface CommentDao {

    void add(Comment comment);

    Optional<Comment> getByUser(User user);

    Optional<List<Comment>> getByPost(Post post);
}
