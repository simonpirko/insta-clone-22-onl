package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Base64;
import java.util.Optional;

@WebServlet("/settings")
public class SettingsServlet extends HttpServlet {
    private final UserService service = UserService.getInstance();
    private User user;
    private final String UPDATE_USER_DATA = "UPDATE \"human\" SET name = ?, surname = ?, username = ?,  email = ?, password = ?\n" +
                                            "WHERE id = ?";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* if (req.getSession().getAttribute("") == null){
            resp.sendRedirect(req.getContextPath() + "/pages/login.jsp");
        } else {}*/
            getServletContext().getRequestDispatcher("/pages/settings.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


       /* if (req.getSession().getAttribute("currentUser") == null){
            resp.sendRedirect(req.getContextPath() + "/pages/login.jsp");
        } else {}*/

            try(Connection connection = JdbcConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_DATA)) {

                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getSurname());
                preparedStatement.setString(3, user.getUsername());
                preparedStatement.setString(4, user.getEmail());
                preparedStatement.setString(5, user.getPassword());
                preparedStatement.setInt(6, user.getId());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    resp.sendRedirect(req.getContextPath() + "/pages/settings.jsp");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/pages/error.jsp");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
}
