package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.entity.Comment;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.Story;
import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.dao.CommentDao.CommentDao;
import by.tms.instaclone22onl.dao.CommentDao.JdbcCommentDao;

import java.util.List;
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

    public Optional<Integer> saveForPost(Comment comment) {
        return commentDao.saveForPost(comment);
    }

    public Optional<Integer> saveForStory(Comment comment) {
        return commentDao.saveForStory(comment);
    }


    public Iterable<Comment> findByPost(Post post) {
        return commentDao.findAllByPost(post);
    }

    public Iterable<Comment> findAllByStory(Story story) {
        return commentDao.findAllByStory(story);
    }


    public Optional<Comment> findAllForPostByUser(User user) {
        return commentDao.findAllForPostByUser(user);
    }

        public Optional<Comment> findAllForStoryByUser(User user) {
        return commentDao.findAllForStoryByUser(user);
    }

    public void removeForPostById(Integer id) {
        commentDao.removeForPostById(id);
    }

    public void removeForStoryById(Integer id) {
        commentDao.removeForStoryById(id);
    }
}

