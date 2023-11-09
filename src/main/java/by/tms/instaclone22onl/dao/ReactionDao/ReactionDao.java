package by.tms.instaclone22onl.dao.ReactionDao;

import by.tms.instaclone22onl.entity.*;

import java.util.Optional;

public interface ReactionDao<ID> {
    Optional<ID> save(Reaction reaction);
    Iterable<Reaction> findAll();
    Optional<Reaction> findByUserAndStory(User user, Story story);
    void removeByUserAndStory(User user,Story story);
    int findAllByStory(Story story);
}

