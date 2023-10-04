package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.service.PostService;
import by.tms.instaclone22onl.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    private final PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getParameter("username");
        Optional<User> userByName = userService.getUserByName(userName);

        if (userByName.isPresent()){
            User user = userByName.get();

            Optional<Post> posts = postService.getPost(user);
            req.setAttribute("post", posts);
            req.setAttribute("user", user);
        }
        getServletContext().getRequestDispatcher("/pages/profile.jsp").forward(req, resp);
    }
}
