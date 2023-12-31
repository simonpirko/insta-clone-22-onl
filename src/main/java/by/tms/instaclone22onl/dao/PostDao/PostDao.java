package by.tms.instaclone22onl.dao.PostDao;

import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.entity.Page;

import java.util.List;
import java.util.Optional;

public interface PostDao<ID> {

    Optional<ID> save(Post post);

    Optional<Post> findById(ID id);

    List<Post> findAllByUser(User user);

    Iterable<Post> findAll();

    Iterable<Post> findAllWithPageable(Page page);

    int countAll();

    void removeById(ID id);

    void removeByUser(User user);

    void updatePost(ID id, Post newPost);

    Optional<ID> saveFavorite(User user, Post post);
    List<Post> findFavorite(User user);
    void removeFavoriteByUser(User user);
}