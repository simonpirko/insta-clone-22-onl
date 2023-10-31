package by.tms.instaclone22onl.dao.StoryDao;

import by.tms.instaclone22onl.entity.Page;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.Story;
import by.tms.instaclone22onl.entity.User;

import java.util.List;
import java.util.Optional;

public interface StoryDao<ID> {
    Optional<ID> save(Story story);

    Optional<Story> findById(ID id);

    List<Story> findAllByUser(User user);

    Iterable<Story> findAll();

    Iterable<Story> findAllWithPageable(Page page);

    int countAll();

    boolean removeById(ID id);

    boolean removeByUser(User user);

    boolean updatePost(ID id, Story newStory);

}
