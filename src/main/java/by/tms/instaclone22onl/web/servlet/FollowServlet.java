package by.tms.instaclone22onl.web.servlet;

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
import java.util.Optional;
import java.util.UUID;

@WebServlet("/follow" )
public class FollowServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        int followerId = Integer.parseInt(req.getParameter("followerId"));
        Optional<Follower> follower =FollowService.getFollowerById(followerId);
        int userId = Integer.parseInt(req.getParameter("userId"));
        Optional<User> user =userService.getUserById(userId);
        FollowService.add(followerId,userId);

        resp.getWriter().write("Now you're a follower");
    }
}
