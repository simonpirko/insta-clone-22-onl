package by.tms.instaclone22onl.adapter;

import by.tms.instaclone22onl.entity.Hashtag;

import java.util.ArrayList;
import java.util.List;

/*
    @author Ilya Moiseenko on 13.10.23
*/

public class HashtagAdapter {

    public List<Hashtag> converToList(String hashtagsString) {
        List<Hashtag> hashtagList = new ArrayList<>();

        hashtagsString = hashtagsString.replaceAll("#", "");
        String[] hashtags = hashtagsString.split(" ");

        for (String hashtag : hashtags) {
            Hashtag newHashtag = Hashtag
                    .builder()
                    .name(hashtag)
                    .build();

            hashtagList.add(newHashtag);
        }

        return hashtagList;
    }
}
