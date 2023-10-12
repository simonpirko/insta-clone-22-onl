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
public class Like {

    private int id;
    private User user;
    private Post post;
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "Like{" +
               "id=" + id +
               ", user=" + user +
               ", post=" + post +
               ", createdAt=" + createdAt +
               '}';
    }
}
