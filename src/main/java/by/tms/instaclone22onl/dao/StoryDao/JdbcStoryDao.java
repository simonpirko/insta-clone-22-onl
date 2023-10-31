package by.tms.instaclone22onl.dao.StoryDao;

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class JdbcStoryDao implements StoryDao<Integer> {
    private static JdbcStoryDao instance;

    private final String INSERT = "INSERT INTO story (author_id, photoorVideo, contenttype, description, created_at) VALUES (?, ?, ?, ?, ?)";
    private final String FIND_BY_ID = """                          
                                      SELECT * FROM story s
                                      JOIN human h 
                                      ON s.author_id = h.id
                                      JOIN country c
                                      ON c.id = h.country_id
                                      WHERE s.id = ?
                                      """;
    private final String FIND_BY_USER = """
                                      SELECT * FROM story s
                                      JOIN human h 
                                      ON s.author_id = h.id
                                      JOIN country c
                                      ON c.id = h.country_id
                                      WHERE s.author_id = ? 
                                      """;

    private final String SELECT_ALL = """
                                    SELECT * FROM story s
                                    JOIN human h ON 
                                    h.id = s.author_id
                                    JOIN country c ON
                                    h.country_id = c.id
                                    """;

    private final String
            SELECT_ALL_FOR_PAGE = """
                                    SELECT * FROM story s
                                    JOIN human h ON
                                    s.author_id = h.id
                                    JOIN country c ON
                                    h.country_id = c.id
                                    LIMIT ? OFFSET ?
                                    """;

    private final String COUNT_ALL = "SELECT COUNT(*) AS total FROM story";

    private final String REMOVE_BY_ID = "DELETE FROM story WHERE id = ?";
    private final String REMOVE_BY_USER = "DELETE FROM story WHERE author_id = ?";
    private final String UPDATE = "UPDATE story SET photoorVideo = ?, contenttype = ?, description = ?, created_at = ? WHERE id = ?";


    private JdbcStoryDao() {
    }

    public static JdbcStoryDao getInstance() {
        if (instance == null) {
            instance = new JdbcStoryDao();
        }
        return instance;
    }

    @Override
    public Optional<Integer> save(Story story) {

        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {

            preparedStatement.setInt(1, story.getUser().getId());
            preparedStatement.setBytes(2, story.getPhotoOrVideo().getBytes());
            preparedStatement.setString(3, story.getDescription());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(story.getCreatedAt()));
            preparedStatement.execute();

            try (ResultSet keys=preparedStatement.getGeneratedKeys()){
                if (keys.next
                        ()) {
                    return Optional.of(keys.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }


    @Override
    public Optional<
            Story> findById(Integer id) {
        try (Connection connection = JdbcConnection.
                getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                Story story = Story.builder()
                        .id(resultSet.getInt(1))
                        .photoOrVideo((Base64.getEncoder().
                                encodeToString(resultSet.
                                        getBytes(3))))
                        .contentType(resultSet.getString(4))
                        .description(resultSet.
                                getString(5))
                        .createdAt(resultSet.getTimestamp(6).toLocalDateTime())
                        .build

                                ();

                User user = User.
                        builder()
                        .id(resultSet.getInt(7))
                        .name(resultSet.getString(8

                        ))
                        .surname(resultSet.getString(9))
                        .username(resultSet
                                .getString(10))
                        .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(11)))
                        .email(resultSet.getString(12))
                        .password(resultSet.getString(13))
                        .build();

                Country country = Country.
                        builder()
                        .id(resultSet.getInt(15))
                        .name(resultSet.getString(16))
                        .build();

                user.setCountry(country);
                story.setUser(user);

                return Optional.of(story);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }


    @Override
    public List<Story> findAllByUser(User user) {

        List<Story> allStoriesByUserList = new ArrayList<>();
        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER)) {
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Story story = Story.builder()
                        .id(resultSet.getInt(1))
                        .user(user)
                        .photoOrVideo((Base64.
                                getEncoder().encodeToString(resultSet.getBytes(3))))
                                .
                        contentType(resultSet.getString(4))
                        .description(resultSet.getString(5))
                        .createdAt(resultSet.getTimestamp(6).toLocalDateTime())
                        .build();

                allStoriesByUserList.add(story);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allStoriesByUserList;
    }


    @Override
    public List<Story> findAll() {
        List<Story> allStoriesList = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Story story = Story.builder()
                        .id(resultSet.getInt(1))
                        .photoOrVideo((Base64.getEncoder().encodeToString(resultSet.getBytes(3))))
                        .contentType(resultSet.getString(4))
                        .description(resultSet.getString(5))
                        .createdAt(resultSet.getTimestamp(6).toLocalDateTime())
                        .build();

                User user = User.builder()
                        .id(resultSet.getInt(7))
                        .name(resultSet.getString(8))
                        .surname(resultSet.getString(9))
                        .username(resultSet.getString(10))
                        .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(11)))
                        .email(resultSet.getString(12))
                        .password(resultSet.getString(13))
                        .build();

                Country country = Country.builder()
                        .id(resultSet.getInt(15))
                        .name(resultSet.getString(16))
                        .build();

                user.setCountry(country);
                story.setUser(user);

                allStoriesList.add(story);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allStoriesList;

    }


    @Override
    public List<Story> findAllWithPageable(Page page) {
        List<Story> storiesForPageList = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FOR_PAGE)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Story story = Story.builder()
                        .id(resultSet.getInt(1))
                        .photoOrVideo((Base64.getEncoder().encodeToString(resultSet.getBytes(3))))
                        .contentType(resultSet.getString(4))
                        .description(resultSet.getString(5))
                        .createdAt(resultSet.getTimestamp(6).toLocalDateTime())
                        .build();

                User user = User.builder()
                        .id(resultSet.getInt(7))
                        .name(resultSet.getString(8))
                        .surname(resultSet.getString(9))
                        .username(resultSet.getString(10))
                        .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(11)))
                        .email(resultSet.getString(12))
                        .password(resultSet.getString(13))
                        .build();

                Country country = Country.builder()
                        .id(resultSet.getInt(15))
                        .name(resultSet.getString(16))
                        .build();

                user.setCountry(country);
                story.setUser(user);

                storiesForPageList.add(story);
            }

        } catch (SQLException e) {
            throw new
                    RuntimeException(e);
        }
        return storiesForPageList;
    }


    @Override
    public int countAll() {
        int sum = 0;

        try (Connection connection = JdbcConnection.getConnection(

        );
             PreparedStatement preparedStatement = connection.
                     prepareStatement
                             (COUNT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery
                    ();

            if (


                    resultSet.
                            next()) {
                sum = resultSet.getInt("total");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sum;
    }


    @Override
    public boolean removeById(Integer id) {
        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                preparedStatement.


                        executeUpdate();
                return true;
            }

        } catch (SQLException e) {
            throw new

                    RuntimeException(e);
        }
        return false;
    }


    @Override
    public boolean
    removeByUser(User user) {
        try (Connection connection = JdbcConnection.
                getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     REMOVE_BY_USER)) {
            preparedStatement.setInt(1,
                    user.getId());
            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if (resultSet.next()) {
                preparedStatement.executeUpdate();
                return true;
            }

        } catch (SQLException e) {


            throw new RuntimeException(e);
        }
        return false;
    }


    @Override
    public boolean updatePost(Integer id, Story newStory) {
        Optional<Story> story = findById(id);

        if (story.isPresent()) {
            try (Connection connection = JdbcConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                preparedStatement.setBytes(1, Base64.getDecoder().decode(newStory.getPhotoOrVideo()));
                preparedStatement.setString(2, newStory.getContentType());
                preparedStatement.setString(3, newStory.getDescription());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(newStory.getCreatedAt()));
                preparedStatement.setInt(5, id);

                preparedStatement.executeUpdate();
                if (newStory.getCreatedAt() != story.get().getCreatedAt()) {
                    return true;
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

}
