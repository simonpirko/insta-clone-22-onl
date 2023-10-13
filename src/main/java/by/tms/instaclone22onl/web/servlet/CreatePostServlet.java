package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.adapter.HashtagAdapter;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.User;
import by.tms.instaclone22onl.service.HashtagService;
import by.tms.instaclone22onl.service.PostService;

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

/*
    @author Ilya Moiseenko on 2.10.23
*/

@WebServlet("/create-post")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class CreatePostServlet extends HttpServlet {

    private final HashtagAdapter hashtagAdapter = new HashtagAdapter();

    private final PostService postService = PostService.getInstance();
    private final HashtagService tagService = HashtagService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/createPost.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part photoPart = req.getPart("photo");
        InputStream photoPartInputStream = photoPart.getInputStream();

        String description = req.getParameter("description");
        User user = (User) req.getSession().getAttribute("user");
        String tags = req.getParameter("tag");

        Post post = Post
                .builder()
                .user(user)
                .description(description)
                .photo(Base64.getEncoder().encodeToString(photoPartInputStream.readAllBytes()))
                .createdAt(LocalDateTime.now())
                .build();

        Optional<Integer> addedPostId = postService.save(post);
        if (addedPostId.isPresent()) {
            Integer postId = addedPostId.get();
            post.setId(postId);
            post.setHashtags(
                    hashtagAdapter.converToList(tags)
            );

            tagService.save(post.getHashtags(), post);
        }

        resp.sendRedirect("/");
    }
}
