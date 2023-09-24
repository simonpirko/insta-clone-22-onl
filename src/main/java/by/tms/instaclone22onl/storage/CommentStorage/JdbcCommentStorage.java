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

    private final String INSERT = "insert into \"comment\" (author_id, post_id, text) values (?, ?, ?)";
    private final String GET_BY_USER = "select * from \"comment\" where author_id = ?";
    private final String GET_BY_POST = "select * from \"comment\" where post_id = ?";

    private UserStorage userStorage = new JdbcUserStorage();
    private PostStorage postStorage = new JdbcPostStorage();

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

                comment.setId(resultSet.getInt(1));
                comment.setUser(userStorage.getById(resultSet.getInt(2)).get());
                comment.setPost(postStorage.getPostById(resultSet.getInt(3)).get());
                comment.setText(resultSet.getString(4));


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

                comment.setId(resultSet.getInt(1));
                comment.setUser(userStorage.getById(resultSet.getInt(2)).get());
                comment.setPost(postStorage.getPostById(resultSet.getInt(3)).get());
                comment.setText(resultSet.getString(4));


                return Optional.of(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
