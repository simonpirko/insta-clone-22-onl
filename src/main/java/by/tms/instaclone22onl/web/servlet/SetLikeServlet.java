package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.model.Like;
import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.service.LikeService;
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

@WebServlet("/like")
public class SetLikeServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();
    private final LikeService likeService = LikeService.getInstance();
    private final PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/like.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("currentUser");
        int postId = Integer.parseInt(req.getParameter("postId"));
        Optional<Post> post = postService.getPost(postId);
        if(post.isEmpty()){
            req.setAttribute("message", "Post not found!");
        }

        else{
            Optional <Like> requiredLike = likeService.getByUserPost(user, post.get());

            if(requiredLike.isEmpty()){
                Like like = requiredLike.get();
                likeService.add(like);
            }
        }

        getServletContext().getRequestDispatcher("/pages/like.jsp").forward(req, resp);

    }

}
