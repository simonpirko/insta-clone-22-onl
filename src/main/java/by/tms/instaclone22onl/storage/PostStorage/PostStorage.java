package by.tms.instaclone22onl.storage.PostStorage;

import by.tms.instaclone22onl.model.Page;
import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;

import java.util.List;
import java.util.Optional;
public interface PostStorage {

    void addPost(Post post);

    Optional<Post> getPost(int id);

    Optional<Post> getPost(User user);

    List<Post> getAllPost();

    List<Post> findAllWithPageable(Page page);

    boolean deletePost(int id);

    boolean deletePost(User user);

    void updatePost(int id, Post newPost);

}