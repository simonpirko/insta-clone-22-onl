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
import java.util.Optional;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {

    private final LikeService likeService = LikeService.getInstance();
    private final PostService postService = PostService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        int postId = Integer.parseInt(req.getParameter("post_id"));

        Optional<Post> postById = postService.getPost(postId);
        if (postById.isPresent()) {
            Post post = postById.get();

            Like like = new Like();
            like.setPost(post);
            like.setUser(user);

            likeService.save(like);

            resp.sendRedirect("/user/viewpost?id=" + postId);
        }
    }
}
