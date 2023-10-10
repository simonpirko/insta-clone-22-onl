package by.tms.instaclone22onl.storage.FollowerStorage;

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.model.Follower;
import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.service.FollowService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

 public  class JdbcFollowerStorage implements FollowerStorage {

    private static JdbcFollowerStorage instance;
    private final String INSERT = "insert into \"followers\"(followerId;userId)values(?,?)";
    private final String GET_BY_ID = "select * from \"followers\"where \"followers\".followerId=?;userId= ?";

    private JdbcFollowerStorage() {
    }

    public static JdbcFollowerStorage getInstance() {
        if (instance == null)
            instance = new JdbcFollowerStorage();
        return instance;
    }
     @Override
     public void add(Follower follower) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, follower.getFollowerId());
            preparedStatement.setInt(2, follower.getIUserId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Follower> getById(int followerId) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1,followerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Follower follower = new Follower();
                follower.setFollowerId(resultSet.getInt(1));
                User user = new User();
                user.setId(resultSet.getInt(2));
                return Optional.of(follower);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}



