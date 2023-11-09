package by.tms.instaclone22onl.web.servlet.StoryServlet;

import by.tms.instaclone22onl.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/delete_story_comment")
public class RemoveCommentStoryServlet extends HttpServlet {

    private final CommentService commentService = CommentService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int commentId = Integer.parseInt(req.getParameter("commentId"));
        int storyId = Integer.parseInt(req.getParameter("storyId"));

        commentService.removeForStoryById(commentId);

        resp.sendRedirect("/user/view_story?id=" + storyId);
    }
}