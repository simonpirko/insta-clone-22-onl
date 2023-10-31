package by.tms.instaclone22onl.dao.CommentDao;

import by.tms.instaclone22onl.entity.Comment;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.Story;
import by.tms.instaclone22onl.entity.User;

import java.util.Optional;

public interface CommentDao<ID> {

    Optional<ID> save(Comment comment);
    Optional<Comment> findAllByUser(User user);
    Iterable<Comment> findAllByPost(Post post);
    Iterable<Comment> findAllByStory(Story story);
    void removeById(ID id);
}
