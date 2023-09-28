package by.tms.instaclone22onl.storage.CommentStorage;

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.model.Comment;
import by.tms.instaclone22onl.model.Country;
import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.storage.PostStorage.JdbcPostStorage;
import by.tms.instaclone22onl.storage.PostStorage.PostStorage;
import by.tms.instaclone22onl.storage.UserStorage.JdbcUserStorage;
import by.tms.instaclone22onl.storage.UserStorage.UserStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Optional;

public class JdbcCommentStorage implements CommentStorage {

    private static JdbcCommentStorage instance;

    private final String INSERT = "insert into \"comment\" (author_id, post_id, text) values (?, ?, ?)";
    private final String GET_BY_USER = "select * from \"comment\" join \"post\" on \"comment\".post_id = \"post\".id where \"comment\".author_id = ?";
    private final String GET_BY_POST = "select * from \"comment\" join \"human\" on \"comment\".author_id = \"human\".id join \"country\" on \"human\".country_id = \"country\".id where \"comment\".post_id = ?";

    private JdbcCommentStorage() {}

    public static JdbcCommentStorage getInstance() {
        if (instance == null)
            instance = new JdbcCommentStorage();

        return instance;
    }

    @Override
    public void add(Comment comment) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);

            preparedStatement.setInt(1, comment.getUser().getId());
            preparedStatement.setInt(2, comment.getPost().getId());
            preparedStatement.setString(3, comment.getText());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Comment> getByUser(User user) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USER);
            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Comment comment = new Comment();

                comment.setUser(user);
                //comment.setPost();
                comment.setText(resultSet.getString(3));

                Post post = new Post();

                post.setId(resultSet.getInt(2));
                post.setUser(user);
                post.setPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(6)));
                post.setDescription(resultSet.getString(7));
                post.setCreatedAt(resultSet.getTimestamp(8).toLocalDateTime());

                comment.setPost(post);

                return Optional.of(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Comment> getByPost(Post post) {

        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_POST);
            preparedStatement.setInt(1, post.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Comment comment = new Comment();

                //comment.setUser(user);
                comment.setPost(post);
                comment.setText(resultSet.getString(3));

                User user = new User();
                user.setId(resultSet.getInt(4));
                user.setName(resultSet.getString(5));
                user.setSurname(resultSet.getString(6));
                user.setUsername(resultSet.getString(7));
                user.setPhoto(
                        Base64.getEncoder().encodeToString(resultSet.getBytes(8))
                );
                user.setEmail(resultSet.getString(9));
                user.setPassword(resultSet.getString(10));
                Country country = new Country(
                        resultSet.getInt(12),
                        resultSet.getString(13)
                );

                user.setCountry(country);

                comment.setUser(user);

                return Optional.of(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
