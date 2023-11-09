package by.tms.instaclone22onl.entity;

/*
    @author Ilya Moiseenko on 19.09.23
*/

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Comment {

    private int id;
    private User user;
    private Post post;
    private Story story;
    private String text;

    @Override
    public String toString() {
        return "Comment{" +
               "id=" + id +
               ", user=" + user +
               ", post=" + post +
               ", story=" + story +
               ", text='" + text + '\'' +
               '}';
    }
}
