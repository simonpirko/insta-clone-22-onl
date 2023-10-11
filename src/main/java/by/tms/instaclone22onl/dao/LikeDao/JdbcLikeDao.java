package by.tms.instaclone22onl.dao.LikeDao;

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.entity.Country;
import by.tms.instaclone22onl.entity.Like;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public final class JdbcLikeDao implements LikeDao {

    private final String LIKE_INSERT = "insert into \"post_like\" (author_id, post_id, created_at) values (?, ?, ?)";
    private final String GET_BY_POST = """
            select h.*, c.name, pl.created_at  from "post_like" pl
                        join "human" h
                        on pl.author_id = h.id
                        join "country" c     
                        on h.country_id  = c.id
                        where pl.post_id =  ?""";
    private final String GET_BY_USER = """                 
            select p.id, p.photo, p.description, p.created_at, pl.created_at from "post_like" pl
            join "post" p
            on pl.post_id = p.id
            where pl.author_id = ?""";

    private final String GET_BY_USER_POST = "select * from \"post_like\" where author_id = ? and post_id = ?";
    private final String SELECT_ALL = """
            select h.*, c.name, p.id, p.photo, p.description, p.created_at, pl.created_at from "post_like" pl
            join "human" h
            on pl.author_id = h.id
            join country c
            on h.country_id = c.id
            join post p
            on pl.post_id = p.id""";
    private final String DELETE_BY_USER_POST = "delete from \"post_like\" where author_id = ? and post_id = ?";

    private final String DELETE_BY_USER = "delete from \"post_like\" where author_id = ?";
    private final String DELETE_BY_POST = "delete from \"post_like\" where post_id = ?";

    private static JdbcLikeDao instance;

    private JdbcLikeDao() {
    }

    public static JdbcLikeDao getInstance() {
        if (instance == null) {
            instance = new JdbcLikeDao();
        }
        return instance;
    }

    @Override
    public boolean add(Like like) {
        Optional<Like> existedLike = getByUserPost(like.getUser(), like.getPost());

        if(existedLike.isPresent()){
            return true;
        }

        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(LIKE_INSERT)) {

            preparedStatement.setInt(1, like.getUser().getId());
            preparedStatement.setInt(2, like.getPost().getId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(like.getCreatedAt()));

            preparedStatement.execute();

            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Like> getByUser(User user) {
        List<Like> likeList = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USER)) {

            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                Post post = Post
                        .builder()
                        .id(resultSet.getInt(1))
                        .description(resultSet.getString(3))
                        .createdAt(resultSet.getTimestamp(4).toLocalDateTime())
                        .user(user)
                        .build();

                byte[] image = resultSet.getBytes(2);
                if (image != null) {
                    post.setPhoto(Base64.getEncoder().encodeToString(image));
                }

                Like like = Like
                        .builder()
                        .user(user)
                        .post(post)
                        .createdAt(resultSet.getTimestamp(5).toLocalDateTime())
                        .build();

                likeList.add(like);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return likeList;
    }

    @Override
    public List<Like> getByPost(Post post) {
        List<Like> likeList = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_POST)) {

            preparedStatement.setInt(1, post.getId());
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

                Like like = Like
                        .builder()
                        .user(user)
                        .post(post)
                        .createdAt(resultSet.getTimestamp(10).toLocalDateTime())
                        .build();

                likeList.add(like);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return likeList;
    }


    @Override
    public Optional<Like> getByUserPost(User user, Post post) {
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
    public List<Like> getAll() {
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
    public boolean deleteByUserPost(User user, Post post) {

        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_USER_POST)) {

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, post.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    @Override
    public boolean deleteByUser(User user) {

        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_USER)) {

            preparedStatement.setInt(1, user.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    @Override
    public boolean deleteByPost(Post post) {

        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_POST)) {

            preparedStatement.setInt(1, post.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


}