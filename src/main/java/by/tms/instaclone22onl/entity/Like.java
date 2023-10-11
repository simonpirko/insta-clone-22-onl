package by.tms.instaclone22onl.entity;

/*
    @author Ilya Moiseenko on 19.09.23
*/

import java.time.LocalDateTime;

public class Like {

    private int id;
    private User user;
    private Post post;
    private LocalDateTime createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

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
