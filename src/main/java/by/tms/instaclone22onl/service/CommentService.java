package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.model.Comment;
import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.storage.CommentStorage.CommentStorage;
import by.tms.instaclone22onl.storage.CommentStorage.JdbcCommentStorage;

import java.util.List;
import java.util.Optional;

public class CommentService {
    private static CommentService instance;
    private final CommentStorage storage = JdbcCommentStorage.getInstance();
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

