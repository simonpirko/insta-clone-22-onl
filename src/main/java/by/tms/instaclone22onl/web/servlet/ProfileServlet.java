package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.service.PostService;
import by.tms.instaclone22onl.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/user/profile")
public class ProfileServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    private final PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getParameter("username");
        Optional<User> userByName = userService.findUserByName(userName);

        if (userByName.isPresent()){
            User user = userByName.get();

            List<Post> posts = postService.findAllByUser(user);
            req.setAttribute("userPosts", posts);
            req.setAttribute("viewedUser", user);
        }

        User sessionUser = (User) req.getSession().getAttribute("user");
        req.setAttribute("user", sessionUser);
        getServletContext().getRequestDispatcher("/pages/profile.jsp").forward(req, resp);
    }
}
