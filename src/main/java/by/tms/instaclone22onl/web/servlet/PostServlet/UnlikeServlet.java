package by.tms.instaclone22onl.web.servlet.PostServlet;

import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.service.LikeService;
import by.tms.instaclone22onl.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/*
    @author Ilya Moiseenko on 2.10.23
*/

@WebServlet("/unlike")
public class UnlikeServlet extends HttpServlet {

    private final LikeService likeService = LikeService.getInstance();
    private final PostService postService = PostService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int post_id = Integer.parseInt(req.getParameter("post_id"));

        Optional<Post> postById = postService.findById((post_id));
        if (postById.isPresent()) {
            likeService.removeByUserAndPost(user, postById.get());
            resp.sendRedirect("/user/viewpost?id=" + post_id);
        }
    }
}
