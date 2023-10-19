package by.tms.instaclone22onl.web.servlet;

/*
    @author Ilya Moiseenko on 25.09.23
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

@WebServlet("/")
public class IndexServlet extends HttpServlet {

    private final PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Post> allPost = postService.getAllPost();
//        req.setAttribute("postList", allPost);

//        getServletContext().getRequestDispatcher("/pages/index.jsp").forward(req, resp);


        User user = (User) req.getSession().getAttribute("user");
        if(user == null){
            resp.sendRedirect("/pages/login.jsp");
        }
else {
            resp.sendRedirect("/page?page=1");
        }

    }

}
