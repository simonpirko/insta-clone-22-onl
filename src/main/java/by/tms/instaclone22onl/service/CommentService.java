package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.entity.Comment;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.dao.CommentDao.CommentDao;
import by.tms.instaclone22onl.dao.CommentDao.JdbcCommentDao;

import java.util.List;
import java.util.Optional;

public class CommentService {
    private static CommentService instance;
    private final CommentDao storage = JdbcCommentDao.getInstance();
    private CommentService(){

    }
    public static CommentService getInstance() {
        if (instance == null)
            instance = new CommentService();

        return instance;
        }
    public void add(Comment comment) {storage.add(comment);}


    public Optional<List<Comment>> getByPost(Post post){return storage.getByPost(post); }

    public Optional<Comment> getByUser(User user){return  storage.getByUser(user);}
}

