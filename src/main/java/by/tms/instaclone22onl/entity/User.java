package by.tms.instaclone22onl.entity;

/*
    @author Ilya Moiseenko on 19.09.23
*/

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
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
