package by.tms.instaclone22onl.post;

import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class PostStorage implements InstagramPost {

    @Override
    public void addPost(Post post) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Post(author, photo, desription) values (?, ?, ?), Statement.RETURN_GENERATED_KEYS");

            preparedStatement.setInt(1, post.getUser().getId());
            preparedStatement.setBytes(2, Base64.getDecoder().decode(post.getPhoto()));
            preparedStatement.setString(3, post.getDescription());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Post> getPostById(int id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from Post where id = ?");

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int authorID = resultSet.getInt("author");
                String photo = resultSet.getBytes("photo");
                String description = resultSet.getString("description")

                return Optional.of(new Post(id, authorID, photo, description))
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty()
    }

    @Override
    public Optional<Post> getPostByUser(User user) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from Post where name = ?");

            preparedStatement.setString(1, user.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int authorID = resultSet.getInt("author");
                String photo = resultSet.getBytes("photo");
                String description = resultSet.getString("description")

                return Optional.of(new Post(id, authorID, photo, description))
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty()
    }

    @Override
    public List<Post> getAllPost() {
        List<Post> posts = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from Post");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int authorID = resultSet.getInt("author");
                String photo = resultSet.getBytes("photo");
                String description = resultSet.getString("description");

                posts.add(new Post(id, authorID, photo, description))
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public boolean deletePostById(int id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE * FROM Post WHERE id = ?");

            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            preparedStatement.close();
            return affectedRows > 0;
        }

    catch(SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean deletePostByUser(User user) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE * FROM Post WHERE name = ?");

            preparedStatement.setInt(1, user.getName());
            int affectedRows = preparedStatement.executeUpdate();
            preparedStatement.close();
            return affectedRows > 0;
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public void updatePost(int id, Post newPost) {

        Optional<Post> post = getPostById(id);
        if (post.isPresent()){
            try {
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Post SET photo = ?, description = ? WHERE id = ?");

                preparedStatement.setBytes(1, Base64.getDecoder().decode(newPost.getPhoto()));
                preparedStatement.setString(2, newPost.getDescription());
                preparedStatement.setInt(3, id);

                preparedStatement.executeUpdate();
                preparedStatement.close();
            }catch (SQLException e){
                throw  new RuntimeException(e)
            }
        }else System.out.println("Post not found");
    }
}
