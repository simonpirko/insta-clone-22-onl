package by.tms.instaclone22onl.web.servlet.StoryServlet;

import by.tms.instaclone22onl.adapter.HashtagAdapter;
import by.tms.instaclone22onl.entity.*;
import by.tms.instaclone22onl.service.CommentService;
import by.tms.instaclone22onl.service.HashtagService;
import by.tms.instaclone22onl.service.StoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@WebServlet("/user/create_story")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)

public class CreateStoryServlet extends HttpServlet {
    private final HashtagAdapter hashtagAdapter = new HashtagAdapter();
    private final StoryService storyService = StoryService.getInstance();
    private final HashtagService hashtagService = HashtagService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/createStory.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        Part partPhotoOrVideo = req.getPart("photoOrVideo");
        InputStream partPhotoOrVideoInputStream = partPhotoOrVideo.getInputStream();

        String description = req.getParameter("description");
        String hashtag = req.getParameter("hashtag");
        String contentType = req.getParameter("contentType");           

        Story story = Story.builder()
                .user(user)
                .photoOrVideo(Base64.getEncoder().encodeToString(partPhotoOrVideoInputStream.readAllBytes()))
                .contentType(Story.Source.valueOf(contentType))
                .description(description)
                .createdAt(LocalDateTime.now())
                .build();

        Optional<Integer> addedStoryId = storyService.save(story);
        if(addedStoryId.isPresent()){
            Integer storyId = addedStoryId.get();
            story.setId(storyId);
            story.setHashtags(hashtagAdapter.converToList(hashtag));
            hashtagService.save(story.getHashtags(), story);
        }

        resp.sendRedirect("/");

    }
}
