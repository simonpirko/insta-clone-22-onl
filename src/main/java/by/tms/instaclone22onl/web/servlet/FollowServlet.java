package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/follow")
public class FollowServlet extends HttpServlet{
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User follower = (User) req.getSession().getAttribute("follower");
        User followee = (User) req.getSession().getAttribute("followee");
        if (follower != null && followee != null) {
            userService.follow(follower, followee);
            resp.getWriter().write("You are  following " + followee.getId());
        } else {
            resp.getWriter().write("Follower is not found");
        }
    }
}
