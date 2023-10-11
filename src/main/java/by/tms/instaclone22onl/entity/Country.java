package by.tms.instaclone22onl.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*
    @author Ilya Moiseenko on 19.09.23
*/

@Getter
@Setter
@Builder
public class Country {

    private int id;
    private String name;
    private List<City> cities;

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
