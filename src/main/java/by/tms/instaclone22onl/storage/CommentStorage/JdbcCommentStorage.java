package by.tms.instaclone22onl.storage.CommentStorage;

public class JdbcCommentStorage {

    private final String INSERT = "insert into \"comment\" (name, surname, username, photo, email, password, countryId) values (?, ?, ?, ?, ?, ?, ?)";
    private final String GET_BY_POST = "select * from \"comment\" where id = ?";
    private final String GET_BY_USER = "select * from \"comment\" where username = ?";
}
