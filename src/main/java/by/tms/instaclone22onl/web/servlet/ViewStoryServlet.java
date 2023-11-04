package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.entity.*;
import by.tms.instaclone22onl.service.*;

import javax.servlet.Registration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/user/view_story")
public class ViewStoryServlet extends HttpServlet {

    StoryService storyService = StoryService.getInstance();
    UserService userService = UserService.getInstance();
    LikeService likeService = LikeService.getInstance();
    CommentService commentService = CommentService.getInstance();
    ReactionService reactionService = ReactionService.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int storyId = Integer.parseInt(req.getParameter("id"));

        Optional<Story> storyById = storyService.findById(storyId);

        if (storyById.isPresent()) {
            Story story = storyById.get();
            req.setAttribute("currentStory", story);

            Optional<Like> currentLikes = likeService.findByUserAndStory(user, story);
            if (currentLikes.isPresent()) {
                req.setAttribute("like", true);
            } else {
                req.setAttribute("like", false);
            }

            Iterable<Comment> currentComments = commentService.findAllByStory(story);
            if (!currentComments.toString().isEmpty()) {            //Normal checking??????????????
                req.setAttribute("comment", true);
            } else {
                req.setAttribute("comment", false);
            }

            Optional<Reaction> currentReactions = reactionService.findByUserAndStory(user, story);
            if (currentReactions.isPresent()) {
                req.setAttribute("reaction", true);
            } else {
                req.setAttribute("reaction", false);
            }

        }

        getServletContext().getRequestDispatcher("/pages/viewstory.jsp").forward(req, resp);
    }
}
