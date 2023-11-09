package by.tms.instaclone22onl.web.servlet.PostServlet;

/*
    @author Ilya Moiseenko on 24.10.23
*/

import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/addfavorite")
public class AddFavoriteServlet extends HttpServlet {

    private final PostService postService = PostService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int postId = Integer.parseInt(req.getParameter("post_id"));
        Optional<Post> favoritePost = postService.findById(postId);

        favoritePost.ifPresent(post -> postService.saveFavorite(user, post));

        resp.sendRedirect("/user/viewpost?id=" + postId);
    }
}
