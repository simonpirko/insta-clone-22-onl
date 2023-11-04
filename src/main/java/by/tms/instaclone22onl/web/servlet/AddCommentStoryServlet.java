package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.entity.Comment;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.Story;
import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.service.CommentService;
import by.tms.instaclone22onl.service.PostService;
import by.tms.instaclone22onl.service.StoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/user/create_story_comment")
public class AddCommentStoryServlet extends HttpServlet {

    private final StoryService storyService = StoryService.getInstance();
    private final CommentService commentService = CommentService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ((User) req.getSession().getAttribute("user"));

        int storyId = Integer.parseInt(req.getParameter("story_id"));
        Optional<Story> story = storyService.findById(storyId);

        if (story.isPresent()) {
            String commentText = req.getParameter("commentMessage");
            Comment comment = Comment.builder()
                    .user(user)
                    .story(story.get())
                    .text(commentText)
                    .build();

            commentService.saveForPost(comment);
            resp.sendRedirect("/user/view_story?id=" + storyId);
        } else {
            req.setAttribute("errormessage", "Story don't found.");
        }
    }
}

