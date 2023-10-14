package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.entity.Page;
import by.tms.instaclone22onl.entity.Post;
import by.tms.instaclone22onl.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/page")
public class PagingServlet extends HttpServlet {
    private final PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
int pageNumber;     //int pageNumber = 1


if(req.getParameter("page") != null){
    pageNumber = Integer.parseInt(req.getParameter("page"));

    Page<Post> page = Page.<Post>builder()
            .pageMin(5)
            .pageMax(5)
            .limit(2)
            .build();

    page.setPageNumber(pageNumber);

    PostService postService = PostService.getInstance();

    Iterable<Post> postsForPageList = postService.getAllWithPageable(page);

    page.setItemsForPageList(postsForPageList);

    int numberOfPosts = postService.countAll();
    page.setTotalItems(numberOfPosts);
    int numberOfPages = page.getTotalPages();

//    req.setAttribute("postList", postsForPageList);
//    req.setAttribute("numbOfPages", numberOfPages);
//    req.setAttribute("currentPage", pageNumber);
    req.setAttribute("page", page);


    getServletContext().getRequestDispatcher("/pages/index.jsp").forward(req, resp);


}
    }


}
