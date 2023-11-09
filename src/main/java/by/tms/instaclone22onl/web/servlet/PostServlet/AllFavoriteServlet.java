package by.tms.instaclone22onl.web.servlet.PostServlet;

/*
    @author Ilya Moiseenko on 25.10.23
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
import java.util.List;

@WebServlet("/allfavorite")
public class AllFavoriteServlet extends HttpServlet {

    private final PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Post> allFavorite = postService.findAllFavorite(user);

        req.setAttribute("allFavorite", allFavorite);

        getServletContext().getRequestDispatcher("/pages/favorite.jsp").forward(req, resp);
    }
}
