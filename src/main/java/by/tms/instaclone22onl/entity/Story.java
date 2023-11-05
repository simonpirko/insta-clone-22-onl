package by.tms.instaclone22onl.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Story {
    private int id;
    private User user;
    private String photoOrVideo;
    private Source contentType;
    private String description;
    private LocalDateTime createdAt;
    private Iterable<Comment> comments;
    private Iterable<Like> likes;
    private Iterable<Reaction> reactions;
    private Iterable<Hashtag> hashtags;


    @Override
    public String toString() {
        return "Story{" +
               "id=" + id +
               ", user=" + user +
               ", photoOrVideo='" + photoOrVideo + '\'' +
               ", contentType=" + contentType +
               ", description='" + description + '\'' +
               ", createdAt=" + createdAt  +
               '}';
    }

    public enum Source {
        PHOTO,
        VIDEO
    }

}



