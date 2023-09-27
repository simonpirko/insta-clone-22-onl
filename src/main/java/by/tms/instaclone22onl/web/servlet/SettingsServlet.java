package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.storage.UserStorage.JdbcUserStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/settings")
public class SettingsServlet extends HttpServlet {
   private JdbcUserStorage storage;
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
        String photo = req.getParameter("photo");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        int userId = Integer.parseInt(req.getParameter("id"));

        currentUser.setName(name);
        currentUser.setSurname(surname);
        currentUser.setUsername(username);
        currentUser.setPhoto(photo);
        currentUser.setUsername(email);
        currentUser.setPassword(password);
        currentUser.setId(userId);

       if (req.getSession().getAttribute("currentUser") == null){
            resp.sendRedirect("/pages/login.jsp");
        } else {
          try {

              storage.updateUserCountry(currentUser.getId(), String.valueOf(currentUser.getCountry()));
              storage.updateById(currentUser);

              resp.sendRedirect("/pages/settings.jsp");
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
       }
    }
}
