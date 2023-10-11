package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.entity.Comment;
import by.tms.instaclone22onl.entity.Like;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.dao.CommentDao.CommentDao;
import by.tms.instaclone22onl.dao.CommentDao.JdbcCommentDao;
import by.tms.instaclone22onl.dao.PostDao.JdbcPostDao;
import by.tms.instaclone22onl.dao.PostDao.PostDao;

import java.util.List;
import java.util.Optional;

public class PostService {

    private static PostService instance;

    private final PostDao postDao = JdbcPostDao.getInstance();
    private final CommentDao commentDao = JdbcCommentDao.getInstance();
    private final LikeService likeService = LikeService.getInstance();

    private PostService() {}

    public static PostService getInstance() {
        if (instance == null) {
            instance = new PostService();
        }

        return instance;
    }

    public void addPost(Post post){
        postDao.addPost(post);
    }
    public Optional<Post> getPost(int id) {
        Optional<Post> optionalPost = postDao.getPost(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            Optional<List<Comment>> commentsByPost = commentDao.getByPost(post);
            List<Like> allLikesByPost = likeService.findAllByPost(post);

            if (commentsByPost.isPresent()) {
                List<Comment> comments = commentsByPost.get();

                post.setComments(comments);
                post.setLikes(allLikesByPost);
            }

            return Optional.of(post);
        }

        return optionalPost;
    }

    public Optional<Post> getPost(User user){
        return postDao.getPost(user);
    }

    public List<Post> getAllPost(){
        return postDao.getAllPost();
    }

    public boolean deletePost(int id){
        return postDao.deletePost(id);
    }

    public boolean deletePost(User user){
        return postDao.deletePost(user);
    }

    public void updatePost(int id, Post newPost){
        postDao.updatePost(id, newPost);
    }
}