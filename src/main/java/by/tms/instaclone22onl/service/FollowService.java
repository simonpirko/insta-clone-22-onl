package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.model.Comment;
import by.tms.instaclone22onl.model.Follower;
import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.storage.CommentStorage.CommentStorage;
import by.tms.instaclone22onl.storage.CommentStorage.JdbcCommentStorage;
import by.tms.instaclone22onl.storage.FollowerStorage.FollowerStorage;
import by.tms.instaclone22onl.storage.FollowerStorage.JdbcFollowerStorage;
import by.tms.instaclone22onl.storage.UserStorage.JdbcUserStorage;
import by.tms.instaclone22onl.storage.UserStorage.UserStorage;

import java.util.Optional;

public class FollowService {

    private static FollowService instance;
    private final FollowerStorage  followerStorage = JdbcFollowerStorage.getInstance();

    private FollowService() {

    }

    public  static FollowService getInstance() {
        if (instance == null)
            instance = new FollowService();

        return instance;
    }
    public void add (Follower follower) {

        followerStorage.add(follower);
    }


    }
