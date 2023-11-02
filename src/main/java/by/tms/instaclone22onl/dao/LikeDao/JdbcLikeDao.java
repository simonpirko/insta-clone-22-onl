package by.tms.instaclone22onl.dao.LikeDao;

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public final class JdbcLikeDao implements LikeDao<Integer> {

    // Fields
    private static JdbcLikeDao instance;

    private final String POST_LIKE_INSERT = "insert into \"post_like\" (author_id, post_id, created_at) values (?, ?, ?)";
    private final String STORY_LIKE_INSERT = "insert into \"story_like\" (author_id, story_id, created_at) values (?, ?, ?)";
    private final String GET_BY_USER_POST = "select * from \"post_like\" where author_id = ? and post_id = ?";
    private final String GET_BY_USER_STORY = "select * from \"story_like\" where author_id = ? and story_id = ?";
    private final String SELECT_ALL = """
            select h.*, c.name, p.id, p.photo, p.description, p.created_at, pl.created_at from "post_like" pl
            join "human" h
            on pl.author_id = h.id
            join country c
            on h.country_id = c.id
            join post p
            on pl.post_id = p.id""";

    private final String SELECT_ALL = """
            select h.*, c.name, s.id, s.photoorvideo, s.description, s.created_at, sl.created_at from "story_like" sl
            join "human" h
            on sl.author_id = h.id
            join country c
            on h.country_id = c.id
            join story s
            on sl.story_id = s.id""";
    private final String DELETE_BY_USER_POST = "delete from \"post_like\" where author_id = ? and post_id = ?";
    private final String DELETE_BY_USER_STORY = "delete from \"story_like\" where author_id = ? and story_id = ?";
    private final String FIND_ALL_BY_POST = "select count (*) from \"post_like\" where post_id = ?";
    private final String FIND_ALL_BY_STORY = "select count(*) from \"story_like\" where story_id = ?";

    // Constructors
    private JdbcLikeDao() {
    }

    // Methods
    public static JdbcLikeDao getInstance() {
        if (instance == null) {
            instance = new JdbcLikeDao();
        }
        return instance;
    }

    @Override
    public Optional<Integer> save(Like like) {
        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(POST_LIKE_INSERT)) {

            preparedStatement.setInt(1, like.getUser().getId());
            preparedStatement.setInt(2, like.getPost().getId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(like.getCreatedAt()));

            preparedStatement.execute();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next())
                    return Optional.of(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }


    @Override
    public Optional<Integer> save(Like like) {
        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(STORY_LIKE_INSERT)) {

            preparedStatement.setInt(1, like.getUser().getId());
            preparedStatement.setInt(2, like.getStory().getId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(like.getCreatedAt()));

            preparedStatement.execute();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next())
                    return Optional.of(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();

        @Override
        public Optional<Like> findByUserAndPost (User user, Post post){
            try (Connection connection = JdbcConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USER_POST)) {

                preparedStatement.setInt(1, user.getId());
                preparedStatement.setInt(2, post.getId());
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    Like like = Like
                            .builder()
                            .user(user)
                            .post(post)
                            .createdAt(resultSet.getTimestamp(3).toLocalDateTime())
                            .build();

                    return Optional.of(like);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return Optional.empty();
        }


        @Override
        public Optional<Like> findByUserAndStory (User user, Story story){
            try (Connection connection = JdbcConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USER_STORY)) {

                preparedStatement.setInt(1, user.getId());
                preparedStatement.setInt(2, story.getId());
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    Like like = Like
                            .builder()
                            .user(user)
                            .story(story)
                            .createdAt(resultSet.getTimestamp(3).toLocalDateTime())
                            .build();

                    return Optional.of(like);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return Optional.empty();
        }

        @Override
        public List<Like> findAll() {
            List<Like> allLikes = new ArrayList<>();

            try (Connection connection = JdbcConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    User user = User
                            .builder()
                            .id(resultSet.getInt(1))
                            .name(resultSet.getString(2))
                            .surname(resultSet.getString(3))
                            .username(resultSet.getString(4))
                            .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(5)))
                            .email(resultSet.getString(6))
                            .password(resultSet.getString(7))
                            .build();

                    Country country = Country
                            .builder()
                            .id(resultSet.getInt(8))
                            .name(resultSet.getString(9))
                            .build();

                    user.setCountry(country);

                    Post post = Post
                            .builder()
                            .id(resultSet.getInt(10))
                            .user(user)
                            .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(11)))
                            .description(resultSet.getString(12))
                            .createdAt(resultSet.getTimestamp(13).toLocalDateTime())
                            .build();

                    Like like = Like
                            .builder()
                            .user(user)
                            .post(post)
                            .createdAt(resultSet.getTimestamp(14).toLocalDateTime())
                            .build();

                    allLikes.add(like);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return allLikes;
        }


        @Override
        public List<Like> findAll() {
            List<Like> allLikes = new ArrayList<>();

            try (Connection connection = JdbcConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    User user = User
                            .builder()
                            .id(resultSet.getInt(1))
                            .name(resultSet.getString(2))
                            .surname(resultSet.getString(3))
                            .username(resultSet.getString(4))
                            .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(5)))
                            .email(resultSet.getString(6))
                            .password(resultSet.getString(7))
                            .build();

                    Country country = Country
                            .builder()
                            .id(resultSet.getInt(8))
                            .name(resultSet.getString(9))
                            .build();

                    user.setCountry(country);

                    Story story = Story
                            .builder()
                            .id(resultSet.getInt(10))
                            .user(user)
                            .photoOrVideo(Source.valueOf(Base64.getEncoder().encodeToString(resultSet.getBytes(11))))
                            .description(resultSet.getString(13))           //???????????? Col. number
                            .createdAt(resultSet.getTimestamp(14).toLocalDateTime())
                            .build();

                    Like like = Like
                            .builder()
                            .user(user)
                            .story(story)
                            .createdAt(resultSet.getTimestamp(14).toLocalDateTime())
                            .build();

                    allLikes.add(like);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return allLikes;
        }

        @Override
        public void removeByUserAndPost (User user, Post post){

            try (Connection connection = JdbcConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_USER_POST)) {

                preparedStatement.setInt(1, user.getId());
                preparedStatement.setInt(2, post.getId());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        @Override
        public void removeByUserAndStory (User user, Story story){

            try (Connection connection = JdbcConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_USER_STORY)) {

                preparedStatement.setInt(1, user.getId());
                preparedStatement.setInt(2, story.getId());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        @Override
        public int findAllByPost (Post post){
            int countOfLike = 0;

            try (Connection connection = JdbcConnection.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_POST);
                preparedStatement.setInt(1, post.getId());

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next())
                    countOfLike = resultSet.getInt(1);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return countOfLike;
        }


        @Override
        public int findAllByStory (Story story){
            int countOfLike = 0;

            try (Connection connection = JdbcConnection.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_STORY);
                preparedStatement.setInt(1, story.getId());

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next())
                    countOfLike = resultSet.getInt(1);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return countOfLike;
        }
    }
}