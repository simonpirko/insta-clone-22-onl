package by.tms.instaclone22onl.dao.CommentDao;

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.entity.Comment;
import by.tms.instaclone22onl.entity.Country;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class JdbcCommentDao implements CommentDao {

    private static JdbcCommentDao instance;

    private final String INSERT = "insert into \"comment\" (author_id, post_id, text) values (?, ?, ?)";
    private final String GET_BY_USER = "select * from \"comment\" join \"post\" on \"comment\".post_id = \"post\".id where \"comment\".author_id = ?";
    private final String GET_BY_POST = "select * from \"comment\" join \"human\" on \"comment\".author_id = \"human\".id join \"country\" on \"human\".country_id = \"country\".id where \"comment\".post_id = ?";

    private JdbcCommentDao() {}

    public static JdbcCommentDao getInstance() {
        if (instance == null)
            instance = new JdbcCommentDao();

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
                Comment comment = Comment
                        .builder()
                        .user(user)
                        .text(resultSet.getString(3))
                        .build();

                Post post = Post
                        .builder()
                        .id(resultSet.getInt(2))
                        .user(user)
                        .photo(
                                Base64.getEncoder().encodeToString(resultSet.getBytes(6))
                        )
                        .description(resultSet.getString(7))
                        .createdAt(resultSet.getTimestamp(8).toLocalDateTime())
                        .build();

                comment.setPost(post);

                return Optional.of(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<List<Comment>> getByPost(Post post) {
        List<Comment> comments = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_POST);
            preparedStatement.setInt(1, post.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Comment comment = Comment
                        .builder()
                        .post(post)
                        .text(resultSet.getString(3))
                        .build();

                User user = User
                        .builder()
                        .id(resultSet.getInt(4))
                        .name(resultSet.getString(5))
                        .surname(resultSet.getString(6))
                        .username(resultSet.getString(7))
                        .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(8)))
                        .email(resultSet.getString(9))
                        .password(resultSet.getString(10))
                        .build();

                Country country = Country
                        .builder()
                        .id(resultSet.getInt(12))
                        .name(resultSet.getString(13))
                        .build();

                user.setCountry(country);

                comment.setUser(user);

                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.of(comments);
    }

}
