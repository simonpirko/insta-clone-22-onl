package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.dao.HashtagDao.HashtagDao;
import by.tms.instaclone22onl.dao.HashtagDao.JdbcHashtagDao;
import by.tms.instaclone22onl.entity.*;
import by.tms.instaclone22onl.dao.CommentDao.CommentDao;
import by.tms.instaclone22onl.dao.CommentDao.JdbcCommentDao;
import by.tms.instaclone22onl.dao.PostDao.JdbcPostDao;
import by.tms.instaclone22onl.dao.PostDao.PostDao;

import java.util.List;
import java.util.Optional;

public class PostService {

    // Fields
    private static PostService instance;

    private final PostDao<Integer> postDao = JdbcPostDao.getInstance();
    private final CommentDao<Integer> commentDao = JdbcCommentDao.getInstance();
    private final HashtagDao<Integer> hashtagDao = JdbcHashtagDao.getInstance();

    // Constructors
    private PostService() {}

    // Methods
    public static PostService getInstance() {
        if (instance == null) {
            instance = new PostService();
        }

        return instance;
    }

    public Optional<Integer> save(Post post){
        return postDao.save(post);
    }

    public Optional<Post> findById(Integer id) {
        Optional<Post> optionalPost = postDao.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            Iterable<Comment> commentsByPost = commentDao.findAllByPost(post);
            post.setComments(commentsByPost);

            Iterable<Hashtag> allHashtagsByPost = hashtagDao.findAllByPost(post);
            post.setHashtags(allHashtagsByPost);

            return Optional.of(post);
        }

        return optionalPost;
    }

    public List<Post> findAllByUser(User user){
        return postDao.findAllByUser(user);
    }

    public Iterable<Post> findAll(){
        return postDao.findAll();
    }

    public Iterable<Post> getAllWithPageable(Page page){
        return postDao.findAllWithPageable(page);
    }

    public int countAll(){
        return postDao.countAll();
    }

    public void removeById(Integer id){
        postDao.removeById(id);
    }

    public void removeByUser(User user){
        postDao.removeByUser(user);
    }

    public void updatePost(int id, Post newPost){
        postDao.updatePost(id, newPost);
    }
}