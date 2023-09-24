package by.tms.instaclone22onl.Storage.LikeStorage;

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.model.Country;
import by.tms.instaclone22onl.model.Like;
import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public final class JdbcLikeStorage implements LikeStorage {

    private final String LIKE_INSERT = "insert into \"post_like\" (author_id, post_id) values (?, ?)";
    private final String GET_BY_POST = """
            select h.*, c.name  from "post_like" pl
                        join "human" h
                        on pl.author_id = h.id
                        join "country" c     
                        on h.country_id  = c.id
                        where pl.post_id =  ?""";
    private final String GET_BY_USER = """                 
            select p.id, p.photo, p.description from "post_like" pl
            join "post" p
            on pl.post_id = p.id
            where pl.author_id = ?""";

    private final String GET_BY_USER_POST = "select * from \"post_like\" where author_id = ? and post_id = ?";
    private final String SELECT_ALL = """
            select h.*, c.name, p.id, p.photo, p.description from "post_like" pl
            join "human" h
            on pl.author_id = h.id
            join country c
            on h.country_id = c.id
            join post p
            on pl.post_id = p.id""";
    private final String DELETE_BY_USER_POST = "delete from \"post_like\" where author_id = ? and post_id = ?";

    private final String DELETE_BY_USER = "delete from \"post_like\" where author_id = ?";
    private final String DELETE_BY_POST = "delete from \"post_like\" where post_id = ?";

    private static JdbcLikeStorage instance;

    private JdbcLikeStorage() {
    }

    public static JdbcLikeStorage getInstance() {
        if (instance == null) {
            instance = new JdbcLikeStorage();
        }
        return instance;
    }

    @Override
    public boolean add(Like like) {

        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(LIKE_INSERT)) {

            preparedStatement.setInt(1, like.getUser().getId());
            preparedStatement.setInt(2, like.getPost().getId());

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


                Post post = new Post();
                post.setId(resultSet.getInt(1));
                post.setUser(user);
                byte[] image = resultSet.getBytes(2);
                if (image != null) {
                    post.setPhoto(Base64.getEncoder().encodeToString(image));
                }

                post.setDescription(resultSet.getString(3));

                Like like = new Like();
                like.setUser(user);
                like.setPost(post);
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


                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setUsername(resultSet.getString(4));

                byte[] image = resultSet.getBytes(5);
                if (image != null) {
                    post.setPhoto(Base64.getEncoder().encodeToString(image));
                }

                user.setEmail(resultSet.getString(6));
                user.setPassword(resultSet.getString(7));

                Country country = new Country();
                country.setId(resultSet.getInt(8));
                country.setName(resultSet.getString(9));
                user.setCountry(country);

                Like like = new Like();
                like.setUser(user);
                like.setPost(post);

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
                Like like = new Like();

                like.setUser(user);
                like.setPost(post);

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
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setUsername(resultSet.getString(4));

                byte[] image = resultSet.getBytes(5);
                if (image != null) {
                    user.setPhoto(Base64.getEncoder().encodeToString(image));
                }
                user.setEmail(resultSet.getString(6));
                user.setPassword(resultSet.getString(7));

                Country country = new Country();
                country.setId(resultSet.getInt(8));
                country.setName(resultSet.getString(9));
                user.setCountry(country);

                Post post = new Post();
                post.setId(resultSet.getInt(10));
                post.setUser(user);

                byte[] image1 = resultSet.getBytes(11);
                if (image1 != null) {
                    post.setPhoto(Base64.getEncoder().encodeToString(image));
                }
                post.setDescription(resultSet.getString(12));

                Like like = new Like();
                like.setUser(user);
                like.setPost(post);

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