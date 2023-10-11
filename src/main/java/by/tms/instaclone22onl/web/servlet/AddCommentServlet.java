package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.entity.Comment;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.service.CommentService;
import by.tms.instaclone22onl.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/user/create-comment")
public class AddCommentServlet extends HttpServlet {

    private final PostService postService = PostService.getInstance();
    private final CommentService commentService = CommentService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ((User) req.getSession().getAttribute("user"));

        int postId = Integer.parseInt(req.getParameter("post_id"));
        Optional<Post> post = postService.getPost(postId);

        if (post.isPresent()) {
            String commentText = req.getParameter("commentMessage");
            Comment comment = Comment.builder()
                    .setUser(user)
                    .setPost(post.get())
                    .setText(commentText)
                    .build();

            commentService.add(comment);
            resp.sendRedirect("/user/viewpost?id=" + postId);
        } else {
            req.setAttribute("errormessage", "Post don't found.");
        }
    }
}
