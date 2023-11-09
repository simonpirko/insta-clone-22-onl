package by.tms.instaclone22onl.dao.PostDao;

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.entity.Country;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.entity.Page;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class JdbcPostDao implements PostDao<Integer> {

    // Fields
    private static JdbcPostDao instance;

    private final String INSERT = "insert into post(author_id, photo, description, created_at) values (?, ?, ?, ?)";
    private final String SELECT_ALL = "select * from post join human on post.author_id = human.id join country on human.country_id = country.id";
    private final String FIND_BY_ID = "select * from post join human on post.author_id = human.id  join country on human.country_id = country.id where post.id = ?";
    private final String FIND_ALL_BY_USER = "select * from post join human on post.author_id = human.id join country on human.country_id = country.id where post.author_id = ?";
    private final String REMOVE_BY_ID = "DELETE  FROM post WHERE id = ?";
    private final String REMOVE_BY_USER = "DELETE FROM Post WHERE id = ?";
    private final String UPDATE = "UPDATE Post SET photo = ?, description = ?, created_at = ? WHERE id = ?";
    private final String SELECT_ALL_FOR_PAGE = """
            SELECT * FROM post p  
            JOIN human h  
            ON p.author_id = h.id  
            JOIN country cn  
            ON h.country_id = cn.id 
            LIMIT ? OFFSET ?
            """;


    private final String COUNT_ALL = "SELECT COUNT(*) AS total FROM post";
    private final String SAVE_FAVORITE = "insert into \"favorite\" (user_id, post_id) values (?, ?)";
    private final String FIND_FAVORITE = "select * from \"favorite\" join \"post\" on \"favorite\".post_id = \"post\".id where \"favorite\".user_id = ?";
    private final String REMOVE_FAVORITE_BY_USER = "delete from \"favorite\" where user_id = ?";

    // Constructors
    private JdbcPostDao() {
    }

    // Methods
    public static JdbcPostDao getInstance() {
        if (instance == null)
            instance = new JdbcPostDao();

        return instance;
    }

    @Override
    public Optional<Integer> save(Post post) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, post.getUser().getId());
            preparedStatement.setBytes(2, Base64.getDecoder().decode(post.getPhoto()));
            preparedStatement.setString(3, post.getDescription());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(post.getCreatedAt()));

            preparedStatement.execute();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    return Optional.of(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Post> findById(Integer id) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Post post = Post
                        .builder()
                        .id(resultSet.getInt(1))
                        .photo((Base64.getEncoder().encodeToString(resultSet.getBytes(3))))
                        .description(resultSet.getString(4))
                        .createdAt(resultSet.getTimestamp(5).toLocalDateTime())
                        .build();

                User user = User
                        .builder()
                        .id(resultSet.getInt(6))
                        .name(resultSet.getString(7))
                        .surname(resultSet.getString(8))
                        .username(resultSet.getString(9))
                        .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(10)))
                        .email(resultSet.getString(11))
                        .password(resultSet.getString(12))
                        .build();

                Country country = Country
                        .builder()
                        .id(resultSet.getInt(14))
                        .name(resultSet.getString(15))
                        .build();

                user.setCountry(country);
                post.setUser(user);

                return Optional.of(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Post> findAllByUser(User user) {
        List<Post> allPostsByUser = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_USER);
            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post = Post
                        .builder()
                        .id(resultSet.getInt(1))
                        .user(user)
                        .photo((Base64.getEncoder().encodeToString(resultSet.getBytes(3))))
                        .description(resultSet.getString(4))
                        .createdAt(resultSet.getTimestamp(5).toLocalDateTime())
                        .build();

                allPostsByUser.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allPostsByUser;
    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Post post = Post
                        .builder()
                        .id(resultSet.getInt(1))
                        .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(3)))
                        .description(resultSet.getString(4))
                        .createdAt(resultSet.getTimestamp(5).toLocalDateTime())
                        .build();

                User user = User
                        .builder()
                        .id(resultSet.getInt(6))
                        .name(resultSet.getString(7))
                        .surname(resultSet.getString(8))
                        .username(resultSet.getString(9))
                        .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(10)))
                        .email(resultSet.getString(11))
                        .password(resultSet.getString(12))
                        .build();

                Country country = Country
                        .builder()
                        .id(resultSet.getInt(14))
                        .name(resultSet.getString(15))
                        .build();

                user.setCountry(country);
                post.setUser(user);

                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Iterable<Post> findAllWithPageable(Page page) {

        List<Post> postsForPageList = new ArrayList<>();

        try {
            Connection connection = JdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FOR_PAGE);

            preparedStatement.setInt(1, page.getLimit());
            preparedStatement.setInt(2, page.getOffset());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Post post = Post
                        .builder()
                        .id(resultSet.getInt(1))
                        .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(3)))
                        .description(resultSet.getString(4))
                        .createdAt(resultSet.getTimestamp(5).toLocalDateTime())
                        .build();

                User user = User.builder()
                        .id(resultSet.getInt(6))
                        .name(resultSet.getString(7))
                        .surname(resultSet.getString(8))
                        .username(resultSet.getString(9))
                        .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(10)))
                        .build();

                Country country = Country.builder()
                        .id(resultSet.getInt(13))
                        .name(resultSet.getString(15))
                        .build();

                user.setCountry(country);
                post.setUser(user);

                postsForPageList.add(post);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return postsForPageList;
    }


    @Override
    public int countAll() {
        int sum = 0;

        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                sum = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sum;
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

    @Override
    public void removeByUser(User user) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_USER);
            preparedStatement.setInt(1, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePost(Integer id, Post newPost) {
        Optional<Post> post = findById(id);

        if (post.isPresent()) {
            try (Connection connection = JdbcConnection.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
                preparedStatement.setBytes(1, Base64.getDecoder().decode(newPost.getPhoto()));
                preparedStatement.setString(2, newPost.getDescription());
                preparedStatement.setTimestamp(3, Timestamp.valueOf(newPost.getCreatedAt()));
                preparedStatement.setInt(4, id);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<Integer> saveFavorite(User user, Post post) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_FAVORITE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, post.getId());

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

    @Override
    public List<Post> findFavorite(User user) {
        List<Post> allFavoritePosts = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_FAVORITE);
            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post = buildPostEntityFromResultSet(resultSet);
                allFavoritePosts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allFavoritePosts;
    }

    @Override
    public void removeFavoriteByUser(User user) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_FAVORITE_BY_USER);
            preparedStatement.setInt(1, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Post buildPostEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Post post = Post
                .builder()
                .id(resultSet.getInt(4))
                .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(5)))
                .description(resultSet.getString(6))
                .createdAt(resultSet.getTimestamp(7).toLocalDateTime())
                .build();

        User user = User
                .builder()
                .id(resultSet.getInt(8))
                .name(resultSet.getString(9))
                .surname(resultSet.getString(10))
                .username(resultSet.getString(11))
                .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(12)))
                .email(resultSet.getString(13))
                .password(resultSet.getString(14))
                .build();

        Country country = Country
                .builder()
                .id(resultSet.getInt(16))
                .name(resultSet.getString(17))
                .build();

        user.setCountry(country);
        post.setUser(user);

        return post;
    }
}
