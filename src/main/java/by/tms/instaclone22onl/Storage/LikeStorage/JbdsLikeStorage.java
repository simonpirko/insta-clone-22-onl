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

    Connection connection;
    int lastID;

    Post post;
    User user;
    public JbdsLikeStorage(Post post, User user){
        this.user = user;
        this.post = post;
    }

    JdbcConnection jdbcConnection;

    public JbdsLikeStorage() {
        connection = JBDCConnection.getConnection();
    }


    private final String LIKE_INSERT = "insert into \"LikeTable\" (id, post, user) values (default, ?, ?)";
    private final String GET_POST = "select * from \"LikeTable\" where post = ?";
    private final String GET_USER = "select * from \"LikeTable\" where user = ?";
    private final String SELECT_ALL = "select * from \"LikeTable\"";

    @Override
    public boolean addLike(Like like) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(LIKE_INSERT);
            preparedStatement.setInt(1, post.getId());            //preparedStatement.setString(1, String.valueOf(like.getPost()));
            preparedStatement.setInt(2, user.getId());             // preparedStatement.setString(2, String.valueOf(like.getUser()));

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                lastID = resultSet.getInt(1);
            }
            preparedStatement.close();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Like> getLikeByUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER);
//            preparedStatement.setString(1, String.valueOf(user));

            preparedStatement.setString(1, user.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Like like = new Like();

//                like.setId(resultSet.getInt(1));
//                like.setPost(resultSet.getString(2));
//                like.setUser(resultSet.getString(3));

                int id = resultSet.getInt(1);
                int postID = resultSet.getInt(2);
                int userID = resultSet.getInt(3);

                return Optional.of(like);
            }
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Like> getLikeByPost(Post post) {        // public Like get(Post post)

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_POST);
//            preparedStatement.setString(1, String.valueOf(post));

            preparedStatement.setInt(1,post.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Like like = new Like();

//               like.setId(resultSet.getInt(1));
//                like.setPost(resultSet.getString(2));
//                like.setUser(resultSet.getString(3));

                int id = resultSet.getInt(1);
                int postID = resultSet.getInt(2);
                int userID = resultSet.getInt(3);


                return Optional.of(like);
            }

            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }



    @Override
    public List<Like> getAllLike(){
        List <Like> allLikes= new ArrayList<>();

        try{
         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);     //Statement statement = connection.createStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Like like = new Like();

                like.setId(resultSet.getInt(1));
                like.setPost(resultSet.getObject(2, Class<Post> Post));
                like.setUser(resultSet.getObject(3, Class<User> User));

                allLikes.add(like);
            }
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allLikes;
    }
}

