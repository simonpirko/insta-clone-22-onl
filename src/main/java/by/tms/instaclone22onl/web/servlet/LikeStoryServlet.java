package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.entity.Like;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.Story;
import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.service.LikeService;
import by.tms.instaclone22onl.service.PostService;
import by.tms.instaclone22onl.service.StoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet("/like_story")
public class LikeStoryServlet extends HttpServlet {

    private final LikeService likeService = LikeService.getInstance();
    private final StoryService storyService = StoryService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        int storyId = Integer.parseInt(req.getParameter("story_id"));

        Optional<Story> storyById = storyService.findById(storyId);
        if (storyById.isPresent()) {
            Story story = storyById.get();

            Like like = Like
                    .builder()
                    .story(story)
                    .user(user)
                    .createdAt(LocalDateTime.now())
                    .build();

            likeService.saveForStory(like);

            resp.sendRedirect("/user/view_story?id=" + storyId);
        }
    }
}
