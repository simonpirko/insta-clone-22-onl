package by.tms.instaclone22onl.web.servlet;

/*
    @author Ilya Moiseenko on 25.09.23
*/

import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Optional<User> userByName = userService.findUserByName(username);

        if (userByName.isPresent()) {
            User user = userByName.get();

            if (user.getPassword().equals(password)) {
                req.getSession().setAttribute("user", user);

                resp.sendRedirect("/");
            } else {
                req.setAttribute("passwordStatus", "Invalid password");
                getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("usernameStatus", "Invalid username");
            getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
        }
    }
}