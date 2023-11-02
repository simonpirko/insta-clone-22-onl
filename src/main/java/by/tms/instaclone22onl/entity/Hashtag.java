package by.tms.instaclone22onl.entity;

/*
    @author Ilya Moiseenko on 13.10.23
*/

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Hashtag {

    private int id;
    private String name;
    private Iterable<Post> posts;
    private Iterable<Story> stories;
}
