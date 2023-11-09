package by.tms.instaclone22onl.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Reaction {
    private int id;
    private User user;
    private Story story;
    private LocalDateTime createdAt;


    @Override
    public String toString() {
        return "Reaction{" +
               "id=" + id +
               ", user=" + user +
               ", story=" + story +
               ", createdAt=" + createdAt +
               '}';
    }
}
