package by.tms.instaclone22onl.entity;

/*
    @author Ilya Moiseenko on 19.09.23
*/

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Post {

    private int id;
    private User user;
    private String photo;
    private String description;
    private LocalDateTime createdAt;
    private Iterable<Comment> comments;
    private Iterable<Like> likes;
    private Iterable<Hashtag> hashtags;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", user=" + user +
                ", photo='" + photo + '\'' +
                ", description='" + description + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
