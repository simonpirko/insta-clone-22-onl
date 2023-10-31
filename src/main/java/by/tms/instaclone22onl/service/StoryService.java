package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.dao.CommentDao.CommentDao;
import by.tms.instaclone22onl.dao.CommentDao.JdbcCommentDao;
import by.tms.instaclone22onl.dao.HashtagDao.HashtagDao;
import by.tms.instaclone22onl.dao.HashtagDao.JdbcHashtagDao;
import by.tms.instaclone22onl.dao.StoryDao.JdbcStoryDao;
import by.tms.instaclone22onl.dao.StoryDao.StoryDao;

public class StoryService {
    private static StoryService instance;
    private final StoryDao<Integer> storyDao = JdbcStoryDao.getInstance();
    private final CommentDao<Integer>  commentDao = JdbcCommentDao.getInstance();
    private final HashtagDao<Integer> hashtagDao = JdbcHashtagDao.getInstance();

    private StoryService(){}

    public static StoryService getInstance(){
        if(instance == null){
            instance = new StoryService();
        }
        return instance;
    }


}
