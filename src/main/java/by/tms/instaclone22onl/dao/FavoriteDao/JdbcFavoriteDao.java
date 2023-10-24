package by.tms.instaclone22onl.dao.FavoriteDao;

/*
    @author Ilya Moiseenko on 24.10.23
*/

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;

import java.sql.*;
import java.util.Optional;

public class JdbcFavoriteDao implements FavoriteDao<Integer> {

    // Fields
    private static JdbcFavoriteDao instance;

    private final String SAVE = "insert into \"favorite\" (post_id, user_id) values (?, ?)";

    // Constructors
    private JdbcFavoriteDao() {}

    // Methods
    public static JdbcFavoriteDao getInstance() {
        if (instance == null)
            instance = new JdbcFavoriteDao();

        return instance;
    }

    @Override
    public Optional<Integer> save(User user, Post post) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, post.getId());
            preparedStatement.setInt(2, user.getId());

            preparedStatement.executeUpdate();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next())
                    return Optional.of(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
