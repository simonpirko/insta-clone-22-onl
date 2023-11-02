package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.dao.CommentDao.CommentDao;
import by.tms.instaclone22onl.dao.CommentDao.JdbcCommentDao;
import by.tms.instaclone22onl.dao.HashtagDao.HashtagDao;
import by.tms.instaclone22onl.dao.HashtagDao.JdbcHashtagDao;
import by.tms.instaclone22onl.dao.StoryDao.JdbcStoryDao;
import by.tms.instaclone22onl.dao.StoryDao.StoryDao;
import by.tms.instaclone22onl.entity.*;

import java.util.List;
import java.util.Optional;

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


    public Optional<Integer> save(Story story){
        storyDao.save(story);
    }

    public Optional<Story> findById(Integer id) {
        Optional <Story> findByIdStory= storyDao.findById(id);

        if(findByIdStory.isPresent()){
            Story story = findByIdStory.get();

            Iterable<Comment> allCommentsByStory = commentDao.findAllByStory(story);
            story.setComments(allCommentsByStory);

            Iterable<Hashtag> allHashtagsByStory = hashtagDao.findAllByStory(story);
            story.setHashtags(allHashtagsByStory);

            return Optional.of(story);
        }
        return findByIdStory;
    }

    public List<Story> findAllByUser(User user) {
        return storyDao.findAllByUser(user);
    }
    public Iterable<Story> findAll() {
        return storyDao.findAll();
    }

    public Iterable<Story> findAllWithPageable(Page page) {
        return storyDao.findAllWithPageable(page);
    }

    public int countAll() {
        return storyDao.countAll();
    }

    public boolean removeById(Integer id) {
        return storyDao.removeById(id);
    }

    public boolean removeByUser(User user) {
        return storyDao.removeByUser(user);
    }

    public boolean updatePost(Integer id, Story newStory) {
        return storyDao.updatePost(id, newStory);
    }

}
