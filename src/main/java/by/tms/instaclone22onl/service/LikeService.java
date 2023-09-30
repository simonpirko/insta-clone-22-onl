package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.model.Like;
import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.storage.LikeStorage.JdbcLikeStorage;
import by.tms.instaclone22onl.storage.LikeStorage.LikeStorage;

import java.util.List;
import java.util.Optional;

public class LikeService {
    private static LikeService instance;
    private final LikeStorage storage = JdbcLikeStorage.getInstance();
    private LikeService(){

    }

    public static LikeService getInstance(){
        if(instance == null){
            instance = new LikeService();
        }
        return instance;
    }

    public boolean add(Like like){
        return storage.add(like);
    }

    public List<Like> getByUser(User user){
        return storage.getByUser(user);
    }

    public List<Like> getByPost(Post post){
        return storage.getByPost(post);
    }

    public Optional<Like> getByUserPost(User user, Post post){
        return storage.getByUserPost(user, post);
    }

    public List<Like> getAll(){
        return storage.getAll();
    }

    public boolean deleteByUserPost(User user, Post post){
        return storage.deleteByUserPost(user, post);
    }

    public boolean deleteByUser(User user){
        return storage.deleteByUser(user);
    }

    public boolean deleteByPost(Post post){
        return storage.deleteByPost(post);
    }

}
