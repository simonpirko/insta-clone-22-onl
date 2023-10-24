package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnfollowServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user=(User)req.getSession().getAttribute ("user");
        int userId =Integer.parseInt( req.getParameter("userId"));

            userService.unfollow(user,userId);
            resp.sendRedirect("/user/?id =" + userId);
            resp.getWriter().write("You have  unfollowed " + userId);
        }

    }


