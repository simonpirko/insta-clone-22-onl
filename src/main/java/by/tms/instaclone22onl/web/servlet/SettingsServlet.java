package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.config.JdbcConnection;
import by.tms.instaclone22onl.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
@WebServlet("/settings")
public class SettingsServlet extends HttpServlet {
    private final String UPDATE_USER_DATA = "UPDATE \"human\" SET name = ?, surname = ?, username = ?,  email = ?, password = ?\n" +
                                            "WHERE id = ?";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       if (req.getSession().getAttribute("currentUser") == null){
            resp.sendRedirect("/pages/login.jsp");
        } else {
           getServletContext().getRequestDispatcher("/pages/settings.j sp").forward(req, resp);
       }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User currentUser = (User) req.getSession().getAttribute("currentUser");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        currentUser.setName(name);
        currentUser.setSurname(surname);
        currentUser.setUsername(username);
        currentUser.setUsername(email);
        currentUser.setPassword(password);

       if (req.getSession().getAttribute("currentUser") == null){
            resp.sendRedirect("/pages/login.jsp");
        } else {
           try(Connection connection = JdbcConnection.getConnection();
               PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_DATA)) {

               preparedStatement.setString(1, currentUser.getName());
               preparedStatement.setString(2, currentUser.getSurname());
               preparedStatement.setString(3, currentUser.getUsername());
               preparedStatement.setString(4, currentUser.getEmail());
               preparedStatement.setString(5, currentUser.getPassword());
               preparedStatement.setInt(6, currentUser.getId());

               int rowsAffected = preparedStatement.executeUpdate();

               if (rowsAffected > 0) {
                   resp.sendRedirect("/pages/settings.jsp");
               } else {
                   resp.sendRedirect("/pages/error.jsp");
               }
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       }
    }
}
