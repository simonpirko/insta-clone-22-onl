package by.tms.instaclone22onl.Storage.LikeStorage;

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.model.Like;
import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JbdsLikeStorage implements LikeStorage {

    private final String LIKE_INSERT = "insert into \"post_like\" (id, post, user) values (default, ?, ?)";
    private final String GET_BY_POST = "select * from \"post_like\" where post_id = ?";
    private final String GET_BY_USER = "select * from \"post_like\" where author_id = ?";
    private final String SELECT_ALL = "select * from \"post_like\"";
    private final String DELETE_BY_USER = "delete from \"post_like\" where author_id = ?";
    private final String DELETE_BY_POST = "delete from \"post_like\" where post_id = ?";


    @Override
    public boolean add(Like like) {

        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(LIKE_INSERT)) {

            preparedStatement.setInt(1, like.getUser().getId());            //preparedStatement.setString(1, String.valueOf(like.getPost()));
            preparedStatement.setInt(2, like.getPost().getId());             // preparedStatement.setString(2, String.valueOf(like.getUser()));

            preparedStatement.execute();

            //What about like ID?

            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Like> getByUser(User user) {

        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USER)) {

            preparedStatement.setString(1, user.getUsername());     //??????????????
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Like like = new Like();

                like.setUser(resultSet.getInt(1));
                like.setPost(resultSet.getString(2));
                like.setId(resultSet.getInt(3));

                return Optional.of(like);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Like> getByPost(Post post) {        // public Like get(Post post)

        try (Connection connection = JdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_POST)) {

            preparedStatement.setInt(1, post.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Like like = new Like();

                like.setUser(resultSet.getInt(1));
                like.setPost(resultSet.getString(2));
                like.setId(resultSet.getInt(3));

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
                Like like = new Like();

                like.setUser(resultSet.getInt(1));
                like.setPost(resultSet.getString(2));
                like.setId(resultSet.getInt(3));;

                allLikes.add(like);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allLikes;
    }


    @Override
    public boolean deleteByUser(User user) {

        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_USER)) {

            preparedStatement.setInt(1, user.getId());

            int affectedRows = preparedStatement.executeUpdate();
if (affectedRows > 0){
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
            if (affectedRows > 0){
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }



}