package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.model.Follower;
import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.service.FollowService;
import by.tms.instaclone22onl.service.PostService;
import by.tms.instaclone22onl.service.UserService;
import by.tms.instaclone22onl.storage.FollowerStorage.FollowerStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/follow" )
public class FollowServlet extends HttpServlet {
    private final FollowService followService = FollowService.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Follower follower = ((Follower) req.getSession().getAttribute("followerId"));
        int userId = Integer.parseInt(req.getParameter("userId"));
        followService.add(follower);

        resp.getWriter().write("Now you're a follower");
    }
}
