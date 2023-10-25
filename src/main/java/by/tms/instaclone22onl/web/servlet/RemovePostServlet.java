package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/profile/removePost")
public class RemovePostServlet extends HttpServlet {
    PostService postService = PostService.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int postId = Integer.parseInt(req.getParameter("postId"));

        if(postService.findById(postId) != null){
            postService.removeById(postId);
        }

        resp.sendRedirect("/user/profile.jsp?username=" + user.getUsername());

    }
}
