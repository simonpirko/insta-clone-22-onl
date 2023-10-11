package by.tms.instaclone22onl.dao.PostDao;

import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;

import java.util.List;
import java.util.Optional;
public interface PostDao {

    void addPost(Post post);

    Optional<Post> getPost(int id);

    Optional<Post> getPost(User user);

    List<Post> getAllPost();

    boolean deletePost(int id);

    boolean deletePost(User user);

    void updatePost(int id, Post newPost);

}