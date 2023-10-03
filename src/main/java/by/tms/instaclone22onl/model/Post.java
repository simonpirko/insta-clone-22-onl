package by.tms.instaclone22onl.model;

/*
    @author Ilya Moiseenko on 19.09.23
*/

import java.time.LocalDateTime;
import java.util.List;

public class Post {

    private int id;
    private User user;
    private String photo;
    private String description;
    private LocalDateTime createdAt;
    private List<Comment> comments;
    private List<Like> likes;

    private Post() {}

    public static PostBuilder builder() {
        return new Post().new PostBuilder();
    }

    public class PostBuilder {

        private PostBuilder() {}

        public PostBuilder id(int id) {
            Post.this.id = id;

            return this;
        }

        public PostBuilder user(User user) {
            Post.this.user = user;

            return this;
        }

        public PostBuilder photo(String photo) {
            Post.this.photo = photo;

            return this;
        }

        public PostBuilder description(String description) {
            Post.this.description = description;

            return this;
        }

        public PostBuilder comments(List<Comment> comments) {
            Post.this.comments = comments;

            return this;
        }

        public PostBuilder createdAt(LocalDateTime localDateTime) {
            Post.this.createdAt = localDateTime;

            return this;
        }

        public PostBuilder likes(List<Like> likes) {
            Post.this.likes = likes;

            return this;
        }

        public Post build() {
            return Post.this;
        }
    }

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

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
