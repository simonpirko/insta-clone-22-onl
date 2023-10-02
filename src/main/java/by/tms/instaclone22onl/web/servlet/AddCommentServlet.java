package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.model.Comment;
import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.service.CommentService;
import by.tms.instaclone22onl.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/user/addcomment") //todo какой путь???
public class AddCommentServlet extends HttpServlet {

    private final PostService postService = PostService.getInstance();
    private final CommentService commentService = CommentService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("").forward(req, resp); //todo jsp на страницу просмотра отдельного поста

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getAttribute("user");

        int postId = Integer.parseInt(req.getParameter("postId"));
        Optional<Post> post = postService.getPost(postId);

        if (post.isPresent()) {
            String commentText = req.getParameter("commentText");
            Comment comment = Comment.builder()
                    .setUser(user)
                    .setPost(post.get())
                    .setText(commentText)
                    .build();

            commentService.add(comment);
            resp.sendRedirect(req.getContextPath() + "user/viewpost?id=" + postId);//todo написать путь на страницу просмотра отдельного поста
        } else {
            req.setAttribute("errormessage", "Post don't found.");
        }
    }
}
