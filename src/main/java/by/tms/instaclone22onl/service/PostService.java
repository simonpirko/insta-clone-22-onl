package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.model.Comment;
import by.tms.instaclone22onl.model.Like;
import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.storage.CommentStorage.CommentStorage;
import by.tms.instaclone22onl.storage.CommentStorage.JdbcCommentStorage;
import by.tms.instaclone22onl.storage.PostStorage.JdbcPostStorage;
import by.tms.instaclone22onl.storage.PostStorage.PostStorage;

import java.util.List;
import java.util.Optional;

public class PostService {

    private static PostService instance;

    private final PostStorage postStorage = JdbcPostStorage.getInstance();
    private final CommentStorage commentStorage = JdbcCommentStorage.getInstance();
    private final LikeService likeService = LikeService.getInstance();

    private PostService() {}

    public static PostService getInstance() {
        if (instance == null) {
            instance = new PostService();
        }
      
        return instance;
    }

    public void addPost(Post post){
        postStorage.addPost(post);
    }
    public Optional<Post> getPost(int id) {
        Optional<Post> optionalPost = postStorage.getPost(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            Optional<List<Comment>> commentsByPost = commentStorage.getByPost(post);
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
        return postStorage.getPost(user);
    }

    public List<Post> getAllPost(){
        return postStorage.getAllPost();
    }

    public boolean deletePost(int id){
        return postStorage.deletePost(id);
    }

    public boolean deletePost(User user){
        return postStorage.deletePost(user);
    }

    public void updatePost(int id, Post newPost){
        postStorage.updatePost(id, newPost);
    }
}
