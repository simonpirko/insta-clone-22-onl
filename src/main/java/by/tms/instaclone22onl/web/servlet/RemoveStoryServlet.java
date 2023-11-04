package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.entity.Story;
import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.service.StoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet("user/remove_story")
public class RemoveStoryServlet extends HttpServlet {
    StoryService storyService = StoryService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int storyId = Integer.parseInt(req.getParameter("storyId"));

        if(storyService.findById(storyId).isPresent()){
            storyService.removeById(storyId);
        }
    }
}
