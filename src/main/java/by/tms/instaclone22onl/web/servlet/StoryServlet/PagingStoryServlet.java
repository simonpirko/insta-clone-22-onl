package by.tms.instaclone22onl.web.servlet.StoryServlet;

import by.tms.instaclone22onl.entity.Page;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.entity.Story;
import by.tms.instaclone22onl.service.PostService;
import by.tms.instaclone22onl.service.StoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/story_page")
public class PagingStoryServlet extends HttpServlet {
    private final StoryService storyService = StoryService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNumber;
        int storiesPerPage;
        if(req.getSession().getAttribute("storiesPerPage") != null){
            storiesPerPage = (int) req.getSession().getAttribute("storiesPerPage");
        }
        else{
            storiesPerPage = 3;
        }

        if (req.getParameter("page") != null) {
            pageNumber = Integer.parseInt(req.getParameter("page"));

            Page<Story> page = Page.<Story>builder()
                    .pageMin(5)
                    .pageMax(5)
                    .limit(storiesPerPage)
                    .build();

            page.setPageNumber(pageNumber);

            Iterable<Story> storiesForPageList = storyService.findAllWithPageable(page);

            page.setItemsForPageList(storiesForPageList);

            int numberOfStories = storyService.countAll();
            page.setTotalItems(numberOfStories);
            int numberOfPages = page.getTotalPages();

            req.setAttribute("page", page);

            getServletContext().getRequestDispatcher("/pages/index.jsp").forward(req, resp);
        }
    }
}