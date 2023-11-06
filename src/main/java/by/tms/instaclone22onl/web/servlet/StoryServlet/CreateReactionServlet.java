package by.tms.instaclone22onl.web.servlet.StoryServlet;

import by.tms.instaclone22onl.entity.Like;
import by.tms.instaclone22onl.entity.Reaction;
import by.tms.instaclone22onl.entity.Story;
import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.service.ReactionService;
import by.tms.instaclone22onl.service.StoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet("/user/story/create_reaction")
public class CreateReactionServlet extends HttpServlet {
    StoryService storyService = StoryService.getInstance();
    ReactionService reactionService = ReactionService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        int storyId = Integer.parseInt(req.getParameter("story_id"));

        Optional<Story> storyById = storyService.findById(storyId);
        if (storyById.isPresent()) {
            Story story = storyById.get();

            Reaction reaction = Reaction.builder()
                    .user(user)
                    .story(story)
                    .createdAt(LocalDateTime.now())
                    .build();

            reactionService.save(reaction);

            resp.sendRedirect("/user/view_story?id=" + storyId);
        }
    }
}
