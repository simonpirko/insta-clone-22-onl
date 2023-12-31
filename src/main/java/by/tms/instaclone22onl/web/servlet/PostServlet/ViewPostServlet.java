package by.tms.instaclone22onl.web.servlet.PostServlet;

import by.tms.instaclone22onl.entity.Like;
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

@WebServlet("/user/viewpost")
public class ViewPostServlet extends HttpServlet {

    private final PostService postService = PostService.getInstance();
    private final LikeService likeService = LikeService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int postId = Integer.valueOf(req.getParameter("id"));
        Optional<Post> postById = postService.findById(postId);

        if (postById.isPresent()) {
            Post post = postById.get();

            User user = (User) req.getSession().getAttribute("user");

            Optional<Like> currentUserLikeStatus = likeService.findByUserAndPost(user, post);

            if (currentUserLikeStatus.isPresent())
                req.setAttribute("like", true);
            else
                req.setAttribute("like", false);

            req.setAttribute("post", post);

            getServletContext().getRequestDispatcher("/pages/viewpost.jsp").forward(req, resp);
        }
    }
}
