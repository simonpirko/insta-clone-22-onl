package by.tms.instaclone22onl.model;

/*
    @author Ilya Moiseenko on 19.09.23
*/

import java.util.List;
import java.util.Set;

public class User {

    private int id;
    private String name;
    private String surname;
    private String username;
    private String photo;
    private String email;
    private String password;
    private Country country;

    private List<Post> posts;
    private List<Comment> comments;
    private List<Like> likes;
    private Set<User> followers;

    public static UserBuilder builder() {
        return new User().new UserBuilder();
    }

    public class UserBuilder {
        private UserBuilder() {
        }

        public UserBuilder setId(int id) {
            User.this.id = id;
            return this;
        }

        public UserBuilder setName(String name) {
            User.this.name = name;
            return this;
        }

        public UserBuilder setSurname(String surname) {
            User.this.surname = surname;
            return this;
        }

        public UserBuilder setUsername(String username) {
            User.this.username = username;
            return this;
        }

        public UserBuilder setPassword(String password) {
            User.this.password = password;
            return this;
        }

        public UserBuilder setEmail(String email) {
            User.this.email = email;
            return this;
        }

        public UserBuilder setCountry(Country country) {
            User.this.country = country;
            return this;
        }

        public UserBuilder setPhoto(String photo) {
            User.this.photo = photo;
            return this;
        }

        public User build() {
            return User.this;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
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
    public Set<User>getFollowers(){return followers;}



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", photo='" + photo + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", countryId=" + country +
                '}';
    }
}
