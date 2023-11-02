package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.dao.ReactionDao.JdbcReactionDao;
import by.tms.instaclone22onl.dao.ReactionDao.ReactionDao;
import by.tms.instaclone22onl.entity.Reaction;
import by.tms.instaclone22onl.entity.Story;
import by.tms.instaclone22onl.entity.User;

import java.util.Optional;

public class ReactionService {
    private static ReactionService instance;
    private final ReactionDao<Integer> reactionDao = JdbcReactionDao.getInstance();

    private ReactionService(){}


    public static ReactionService getInstance(){
        if(instance == null){
            instance = new ReactionService();
        }
        return instance;
    }


    public Optional<Integer> save(Reaction reaction){
        return reactionDao.save(reaction);
    }
    public Iterable<Reaction> findAll(){
        return reactionDao.findAll();
    }
    public Optional<Reaction> findByUserAndStory(User user, Story story){
        return reactionDao.findByUserAndStory(user, story);
    }
    public void removeByUserAndStory(User user,Story story){
        reactionDao.removeByUserAndStory(user, story);
    }
    public int findAllByStory(Story story){
        return reactionDao.findAllByStory(story);
    }

}
