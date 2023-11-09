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

public class PagingStoryServletHelper {
    private static StoryService storyService = StoryService.getInstance();

    public static void getStoryPages(HttpServletRequest req){
        int pageNumber = 1;
        int storiesPerPage;
        if(req.getSession().getAttribute("storiesPerPage") != null){
            storiesPerPage = (int) req.getSession().getAttribute("storiesPerPage");
        }
        else{
            storiesPerPage = 3;
        }

        if (req.getParameter("storyPage") != null) {
            pageNumber = Integer.parseInt(req.getParameter("storyPage"));
        }
            Page<Story> storyPage = Page.<Story>builder()
                    .pageMin(5)
                    .pageMax(5)
                    .limit(storiesPerPage)
                    .build();

            storyPage.setPageNumber(pageNumber);

            Iterable<Story> storiesForPageList = storyService.findAllWithPageable(storyPage);

            storyPage.setItemsForPageList(storiesForPageList);

            int numberOfStories = storyService.countAll();
            storyPage.setTotalItems(numberOfStories);
            int numberOfPages = storyPage.getTotalPages();
            req.setAttribute("storyPage", storyPage);

//            getServletContext().getRequestDispatcher("/pages/index.jsp").forward(req, resp);
        }
    }
