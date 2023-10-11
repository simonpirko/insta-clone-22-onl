package by.tms.instaclone22onl.dao.PostDao;

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.entity.Country;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class JdbcPostDao implements PostDao {

    private static JdbcPostDao instance;

    private final String SELECT_ALL = "select * from post join human on post.author_id = human.id join country on human.country_id = country.id";
  
    private JdbcPostDao() {}

    public static JdbcPostDao getInstance() {
        if (instance == null)
            instance = new JdbcPostDao();

        return instance;
    }

    @Override
    public void addPost(Post post) {
        try {
            Connection connection = JdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into post(author_id, photo, description, created_at) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, post.getUser().getId());
            preparedStatement.setBytes(2, Base64.getDecoder().decode(post.getPhoto()));
            preparedStatement.setString(3, post.getDescription());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(post.getCreatedAt()));
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
                Post post = Post
                        .builder()
                        .id(resultSet.getInt(1))
                        .photo((Base64.getEncoder().encodeToString(resultSet.getBytes(3))))
                        .description(resultSet.getString(4))
                        .createdAt(resultSet.getTimestamp(5).toLocalDateTime())
                        .build();

                User user = new User();
                user.setId(resultSet.getInt(6));
                user.setName(resultSet.getString(7));
                user.setSurname(resultSet.getString(8));
                user.setUsername(resultSet.getString(9));
                user.setPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(10)));
                user.setEmail(resultSet.getString(11));
                user.setPassword(resultSet.getString(12));

                Country country = new Country(
                        resultSet.getInt(14),
                        resultSet.getString(15)
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
                Post post = Post
                        .builder()
                        .id(resultSet.getInt(1))
                        .photo((Base64.getEncoder().encodeToString(resultSet.getBytes(3))))
                        .description(resultSet.getString(4))
                        .createdAt(resultSet.getTimestamp(5).toLocalDateTime())
                        .build();

                user.setId(resultSet.getInt(6));
                user.setName(resultSet.getString(7));
                user.setSurname(resultSet.getString(8));
                user.setUsername(resultSet.getString(9));
                user.setPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(10)));
                user.setEmail(resultSet.getString(11));
                user.setPassword(resultSet.getString(12));

                Country country = new Country(
                        resultSet.getInt(14),
                        resultSet.getString(15)
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

        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Post post = Post
                        .builder()
                        .id(resultSet.getInt(1))
                        .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(3)))
                        .description(resultSet.getString(4))
                        .createdAt(resultSet.getTimestamp(5).toLocalDateTime())
                        .build();

                User user = new User();
                user.setId(resultSet.getInt(6));
                user.setName(resultSet.getString(7));
                user.setSurname(resultSet.getString(8));
                user.setUsername(resultSet.getString(9));
                user.setPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(10)));
                user.setEmail(resultSet.getString(11));
                user.setPassword(resultSet.getString(12));

                Country country = new Country(
                        resultSet.getInt(14),
                        resultSet.getString(15)
                );

                user.setCountry(country);
                post.setUser(user);

                posts.add(post);
            }
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
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Post SET photo = ?, description = ?, created_at = ? WHERE id = ?");

                preparedStatement.setBytes(1, Base64.getDecoder().decode(newPost.getPhoto()));
                preparedStatement.setString(2, newPost.getDescription());
                preparedStatement.setTimestamp(3, Timestamp.valueOf(newPost.getCreatedAt()));
                preparedStatement.setInt(4, id);

                preparedStatement.executeUpdate();
                preparedStatement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else System.out.println("Post not found");
    }
}
