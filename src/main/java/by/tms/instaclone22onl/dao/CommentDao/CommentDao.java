package by.tms.instaclone22onl.dao.CommentDao;

import by.tms.instaclone22onl.entity.Comment;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.Story;
import by.tms.instaclone22onl.entity.User;

import java.util.Optional;

public interface CommentDao<ID> {

    Optional<ID> saveForPost(Comment comment);
    Optional<ID> saveForStory(Comment comment);
    Optional<Comment> findAllForPostByUser(User user);
    Optional<Comment> findAllForStoryByUser(User user);
    Iterable<Comment> findAllByPost(Post post);
    Iterable<Comment> findAllByStory(Story story);
    void removeForPostById(ID id);
    void removeForStoryById(ID id);

}
