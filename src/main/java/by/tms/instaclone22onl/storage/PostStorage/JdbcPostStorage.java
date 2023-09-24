package by.tms.instaclone22onl.storage.PostStorage;

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.model.Country;
import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class JdbcPostStorage implements PostStorage {

    @Override
    public void addPost(Post post) {
        try {
            Connection connection = JdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into post(author_id, photo, description) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, post.getUser().getId());
            preparedStatement.setBytes(2, Base64.getDecoder().decode(post.getPhoto()));
            preparedStatement.setString(3, post.getDescription());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Post> getPost(int id) {
        try {
            Connection connection = JdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from post join human" +
                    " on post.author_id = human.id  join country on human.country_id = country.id where post.id = ?");

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                Post post = new Post();
                post.setId(resultSet.getInt(1));
                post.setPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(3)));
                post.setDescription(resultSet.getString(4));

                User user = new User();
                user.setId(resultSet.getInt(5));
                user.setName(resultSet.getString(6));
                user.setSurname(resultSet.getString(7));
                user.setUsername(resultSet.getString(8));
                user.setPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(9)));
                user.setEmail(resultSet.getString(10));
                user.setPassword(resultSet.getString(11));

                Country country = new Country(
                        resultSet.getInt(13),
                        resultSet.getString(12)
                );

                user.setCountry(country);
                post.setUser(user);
                return Optional.of(post);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Post> getPost(User user) {
        try {
            Connection connection =JdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from post join human" +
                    " on post.author_id = human.id  join country on human.country_id = country.id where human.name = ?");

            preparedStatement.setString(1, user.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getInt(1));
                post.setPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(3)));
                post.setDescription(resultSet.getString(4));

                user.setId(resultSet.getInt(5));
                user.setName(resultSet.getString(6));
                user.setSurname(resultSet.getString(7));
                user.setUsername(resultSet.getString(8));
                user.setPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(9)));
                user.setEmail(resultSet.getString(10));
                user.setPassword(resultSet.getString(11));

                Country country = new Country(
                        resultSet.getInt(13),
                        resultSet.getString(12)
                );

                user.setCountry(country);
                post.setUser(user);

                return Optional.of(post);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Post> getAllPost() {
        List<Post> posts = new ArrayList<>();
        try {
            Connection connection =JdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from post join human" +
                    " on post.author_id = human.id  join country on human.country_id = country.id");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Post post = new Post();
                post.setId(resultSet.getInt(1));
                post.setPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(3)));
                post.setDescription(resultSet.getString(4));

                User user = new User();
                user.setId(resultSet.getInt(5));
                user.setName(resultSet.getString(6));
                user.setSurname(resultSet.getString(7));
                user.setUsername(resultSet.getString(8));
                user.setPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(9)));
                user.setEmail(resultSet.getString(10));
                user.setPassword(resultSet.getString(11));

                Country country = new Country(
                        resultSet.getInt(13),
                        resultSet.getString(12)
                );

                user.setCountry(country);
                post.setUser(user);

                posts.add(post);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public boolean deletePost(int id) {
        try {
            Connection connection =JdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE  FROM Post WHERE id = ?");

            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            preparedStatement.close();
            return affectedRows > 0;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletePost(User user) {
        try {
            Connection connection =JdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Post WHERE id = ?");

            preparedStatement.setInt(1, user.getId());
            int affectedRows = preparedStatement.executeUpdate();
            preparedStatement.close();
            return affectedRows > 0;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void updatePost(int id, Post newPost) {
        Optional<Post> post = getPost(id);
        if (post.isPresent()){
            try {
                Connection connection = JdbcConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Post SET photo = ?, description = ? WHERE id = ?");

                preparedStatement.setBytes(1, Base64.getDecoder().decode(newPost.getPhoto()));
                preparedStatement.setString(2, newPost.getDescription());
                preparedStatement.setInt(3, id);

                preparedStatement.executeUpdate();
                preparedStatement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else System.out.println("Post not found");
    }
}
