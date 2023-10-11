package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

   private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/search.jsp").forward(req, resp);
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("OtherUsername");

        List <User> usersByUsername = userService.getUsersWithUsernameContaining(username);

        if(!usersByUsername.isEmpty()){

            req.setAttribute("users", usersByUsername);

        }
        else {
            req.setAttribute("message", "Username not found!");
        }
        getServletContext().getRequestDispatcher("/pages/search.jsp").forward(req, resp);
    }
}
