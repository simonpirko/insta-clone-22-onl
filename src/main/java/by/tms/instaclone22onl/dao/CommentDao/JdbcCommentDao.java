package by.tms.instaclone22onl.dao.CommentDao;

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class JdbcCommentDao implements CommentDao<Integer> {

    // Fields
    private static JdbcCommentDao instance;

    private final String INSERT = "insert into \"post_comment\" (author_id, post_id, text) values (?, ?, ?)";
    private final String GET_BY_USER = "select * from \"post_comment\" join \"post\" on \"post_comment\".post_id = \"post\".id where \"post_comment\".author_id = ?";
    private final String GET_BY_POST = "select * from \"post_comment\" join \"human\" on \"post_comment\".author_id = \"human\".id join \"country\" on \"human\".country_id = \"country\".id where \"post_comment\".post_id = ?";
    private final String GET_BY_STORY = """
            SELECT * FROM story_comment sc
            JOIN human h
            ON sc.author_id = h.id
            JOIN country c
            ON h.country_id = c.id
            WHERE sc.story_id = ?
            """;
    private final String REMOVE_BY_ID = "delete from \"post_comment\" where id = ?";

    // Constructors
    private JdbcCommentDao() {}

    // Methods
    public static JdbcCommentDao getInstance() {
        if (instance == null)
            instance = new JdbcCommentDao();

        return instance;
    }

    @Override
    public Optional<Integer> save(Comment comment) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);

            preparedStatement.setInt(1, comment.getUser().getId());
            preparedStatement.setInt(2, comment.getPost().getId());
            preparedStatement.setString(3, comment.getText());

            preparedStatement.execute();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next())
                    return Optional.of(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Comment> findAllByUser(User user) {
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
    public List<Comment> findAllByPost(Post post) {
        List<Comment> comments = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_POST);
            preparedStatement.setInt(1, post.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Comment comment = Comment
                        .builder()
                        .id(resultSet.getInt(1))
                        .post(post)
                        .text(resultSet.getString(4))
                        .build();

                User user = User
                        .builder()
                        .id(resultSet.getInt(5))
                        .name(resultSet.getString(6))
                        .surname(resultSet.getString(7))
                        .username(resultSet.getString(8))
                        .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(9)))
                        .email(resultSet.getString(10))
                        .password(resultSet.getString(11))
                        .build();

                Country country = Country
                        .builder()
                        .id(resultSet.getInt(13))
                        .name(resultSet.getString(14))
                        .build();

                user.setCountry(country);

                comment.setUser(user);

                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comments;
    }


    @Override
    public List<Comment> findAllByStory(Story story) {
        List<Comment> allCommentsByStoryList = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_STORY)) {
            preparedStatement.setInt(1, story.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Comment storyComment = Comment.builder()
                        .id(resultSet.getInt(1))
                        .story(story)
                        .text(resultSet.getString(4))
                        .build();

                User user = User.builder()
                        .id(resultSet.getInt(5))
                        .name(resultSet.getString(6))
                        .surname(resultSet.getString(7))
                        .username(resultSet.getString(8))
                        .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(9)))
                        .email(resultSet.getString(10))
                        .password(resultSet.getString(11))
                        .build();

                Country country = Country.builder()
                        .id(resultSet.getInt(13))
                        .name(resultSet.getString(14))
                        .build();

                user.setCountry(country);
                storyComment.setUser(user);
                storyComment.setStory(story);

                allCommentsByStoryList.add(storyComment);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allCommentsByStoryList;

    }

    @Override
    public void removeById(Integer id) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
