package by.tms.instaclone22onl.storage.CommentStorage;

public class JdbcCommentStorage {

    private final String INSERT = "insert into \"comment\" (author_id, post_id, text) values (?, ?, ?)";
    private final String GET_BY_USER = "select * from \"comment\" where author_id = ?";
    private final String GET_BY_POST = "select * from \"comment\" where post_id = ?";
}
