package by.tms.instaclone22onl.service;

import by.tms.instaclone22onl.dao.HashtagDao.HashtagDao;
import by.tms.instaclone22onl.dao.HashtagDao.JdbcHashtagDao;
import by.tms.instaclone22onl.entity.Hashtag;
import by.tms.instaclone22onl.entity.Post;

import java.util.Optional;

/*
    @author Ilya Moiseenko on 13.10.23
*/

public class HashtagService {

    private static HashtagService instance;

    private final HashtagDao<Integer> tagDao = JdbcHashtagDao.getInstance();

    private HashtagService() {}

    public static HashtagService getInstance() {
        if (instance == null)
            instance = new HashtagService();

        return instance;
    }

    public void save(Iterable<Hashtag> hashtags, Post post) {
        for (Hashtag hashtag : hashtags) {
            Optional<Hashtag> hashtagByName = this.findByName(hashtag.getName());
            if (!hashtagByName.isPresent())
                tagDao.save(hashtag);

            Optional<Hashtag> hashtagForPost = this.findByName(hashtag.getName());
            if (hashtagForPost.isPresent()) {
                Hashtag curr = hashtagForPost.get();

                tagDao.saveForPost(curr, post);
            }
        }
    }

    public Optional<Hashtag> findByName(String name) {
        return tagDao.findByName(name);
    }

    public Iterable<Hashtag> findAllByPost(Post post) {
        return tagDao.findAllByPost(post);
    }
}
