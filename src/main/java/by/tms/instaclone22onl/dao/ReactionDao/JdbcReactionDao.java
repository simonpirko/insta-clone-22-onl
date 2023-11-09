package by.tms.instaclone22onl.dao.ReactionDao;

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.dao.LikeDao.JdbcLikeDao;
import by.tms.instaclone22onl.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class JdbcReactionDao implements ReactionDao<Integer>{

    private static JdbcReactionDao instance;

    private final String INSERT = "INSERT INTO story_reactions (author_id, story_id, created_at) values (?, ?, ?)";
    private final String SELECT_ALL = """
            select h.*, c.name, s.id, s.photoorvideo, s.description, s.created_at, sr.created_at FROM story_reactions sr
            join "human" h
            on sr.author_id = h.id
            join country c
            on h.country_id = c.id
            join story s
            on sr.story_id = s.id""";

    private final String GET_BY_USER_STORY = "SELECT * FROM story_reactions WHERE author_id = ? AND story_id = ?";
    private final String DELETE_BY_USER_STORY = "DELETE FROM story_reactions WHERE author_id = ? AND story_id = ?";
    private final String FIND_ALL_BY_STORY = "SELECT count(*) FROM story_reactions WHERE story_id = ?";


    private JdbcReactionDao(){}

    public static JdbcReactionDao getInstance(){
        if(instance == null){
            instance = new JdbcReactionDao();
        }
        return instance;
    }

    @Override
    public Optional<Integer> save(Reaction reaction){
            try (Connection connection = JdbcConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {

                preparedStatement.setInt(1, reaction.getUser().getId());
                preparedStatement.setInt(2, reaction.getStory().getId());
                preparedStatement.setTimestamp(3, Timestamp.valueOf(reaction.getCreatedAt()));

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
    public Iterable<Reaction> findAll(){
            List<Reaction> allReactions = new ArrayList<>();

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
                            .photoOrVideo(Base64.getEncoder().encodeToString(resultSet.getBytes(11)))
                            .description(resultSet.getString(13))           //???????????? Col. number
                            .createdAt(resultSet.getTimestamp(14).toLocalDateTime())
                            .build();

                    Reaction reaction = Reaction
                            .builder()
                            .user(user)
                            .story(story)
                            .createdAt(resultSet.getTimestamp(14).toLocalDateTime())
                            .build();

                    allReactions.add(reaction);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return allReactions;
        }


    @Override
    public Optional<Reaction> findByUserAndStory(User user, Story story){
            try (Connection connection = JdbcConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USER_STORY)) {

                preparedStatement.setInt(1, user.getId());
                preparedStatement.setInt(2, story.getId());
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    Reaction reaction = Reaction
                            .builder()
                            .user(user)
                            .story(story)
                            .createdAt(resultSet.getTimestamp(3).toLocalDateTime())
                            .build();

                    return Optional.of(reaction);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return Optional.empty();
        }


    @Override
    public void removeByUserAndStory(User user,Story story){
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
   public int findAllByStory(Story story){
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

