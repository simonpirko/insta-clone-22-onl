package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.model.Like;
import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.service.LikeService;
import by.tms.instaclone22onl.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
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
        Optional<Post> postById = postService.getPost(postId);

        if (postById.isPresent()) {
            Post post = postById.get();
            List<Like> likes = likeService.findAllByPost(post);

            User user = (User) req.getSession().getAttribute("user");

            Optional<Like> currentUserLikeStatus = likeService.findByUserAndPost(user, post);

            if (currentUserLikeStatus.isPresent())
                req.setAttribute("like", true);
            else
                req.setAttribute("like", false);

            req.setAttribute("post", post);
            req.setAttribute("likes", likes.size());

            getServletContext().getRequestDispatcher("/pages/viewpost.jsp").forward(req, resp);
        }
    }
}
