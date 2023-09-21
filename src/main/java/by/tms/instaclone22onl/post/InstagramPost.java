package by.tms.instaclone22onl.post;

import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;

import java.util.List;
import java.util.Optional;

public interface InstagramPost {
   
    void addPost(Post post);

    Optional<Post> getPostById(int id);

    Optional<Post> getPostByUser(User user);

    List<Post> getAllPost();

    boolean deletePostById(int id);

    boolean deletePostByUser(User user);

    void updatePost(int id, Post newPost);
    
}