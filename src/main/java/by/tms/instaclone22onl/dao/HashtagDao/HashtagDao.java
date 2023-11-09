package by.tms.instaclone22onl.dao.HashtagDao;

import by.tms.instaclone22onl.entity.Hashtag;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.Story;

import java.util.Optional;

/*
    @author Ilya Moiseenko on 13.10.23
*/
public interface HashtagDao<ID> {

    ID save(Hashtag hashtag);
    Optional<Hashtag> findByName(String name);
    Iterable<Hashtag> findAllByPost(Post post);
    Iterable<Hashtag> findAllByStory(Story story);
    Optional<ID> saveForPost(Hashtag hashtag, Post post);
    Optional<ID> saveForStory(Hashtag hashtag, Story story);
}
