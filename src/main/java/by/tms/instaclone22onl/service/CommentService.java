package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.entity.Comment;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.dao.CommentDao.CommentDao;
import by.tms.instaclone22onl.dao.CommentDao.JdbcCommentDao;

import java.util.Optional;

public class CommentService {

    // Fields
    private static CommentService instance;

    private final CommentDao<Integer> commentDao = JdbcCommentDao.getInstance();

    // Constructors
    private CommentService() {}

    // Methods
    public static CommentService getInstance() {
        if (instance == null)
            instance = new CommentService();

        return instance;
    }

    public Optional<Integer> save(Comment comment) {
        return commentDao.save(comment);
    }

    public Iterable<Comment> findByPost(Post post) {
        return commentDao.findAllByPost(post);
    }

    public Optional<Comment> findByUser(User user) {
        return commentDao.findAllByUser(user);
    }

    public void removeById(Integer id) {
        commentDao.removeById(id);
    }
}

