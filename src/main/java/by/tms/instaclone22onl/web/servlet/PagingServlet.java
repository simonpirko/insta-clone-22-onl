package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.model.Page;
import by.tms.instaclone22onl.model.Post;
import by.tms.instaclone22onl.service.PageService;
import by.tms.instaclone22onl.service.PostService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/page")
public class PagingServlet extends HttpServlet {
    private final PageService pageService = new PageService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
int pageNumber;     //int pageNumber = 1


if(req.getParameter("page") != null){
    pageNumber = Integer.parseInt(req.getParameter("page"));
    Page page = new Page();
    page.setPageNumber(pageNumber);
    page.setLimit(2);

    PostService postService = PostService.getInstance();

    List<Post> postsForPageList = postService.getAllWithPageable(page);

    int numberOfPosts = postService.getAllPost().size();
    int numberOfPages = (int) Math.ceil(numberOfPosts * 1.0 / postsForPageList.size());

    req.setAttribute("postList", postsForPageList);
    req.setAttribute("numbOfPages", numberOfPages);
    req.setAttribute("currentPage", pageNumber);

    getServletContext().getRequestDispatcher("/pages/index.jsp").forward(req, resp);


}
    }


}
