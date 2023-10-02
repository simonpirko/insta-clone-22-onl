package by.tms.instaclone22onl.model;

/*
    @author Ilya Moiseenko on 19.09.23
*/

public class Comment {
    private int id;
    private User user;
    private Post post;
    private String text;

    public Comment() {
    }

    public static Comment.CommentBuilder builder() {
        return new Comment().new CommentBuilder();
    }

    public class CommentBuilder {
        private CommentBuilder() {
        }

        public Comment.CommentBuilder setId(int id) {
            Comment.this.id = id;
            return this;
        }

        public Comment.CommentBuilder setText(String text) {
            Comment.this.text = text;
            return this;
        }

        public Comment.CommentBuilder setUser(User user) {
            Comment.this.user = user;
            return this;
        }

        public Comment.CommentBuilder setPost(Post post) {
            Comment.this.post = post;
            return this;
        }

        public Comment build() {
            return Comment.this;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", post=" + post +
                ", text='" + text + '\'' +
                '}';
    }
}
