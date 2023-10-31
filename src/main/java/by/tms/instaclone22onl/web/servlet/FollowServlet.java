package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/follow")
public class FollowServlet extends HttpServlet{
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String userName = req.getParameter("username");
        userService.getUserByName(userName);
        int userId =Integer.parseInt( req.getParameter("userId"));
        userService.follow( user,userId);
        resp.sendRedirect("/profile/?username/?id =" + user);


        }
    }

