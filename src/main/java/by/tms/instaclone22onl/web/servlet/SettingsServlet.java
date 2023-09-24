package by.tms.instaclone22onl.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/settings")
public class SettingsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("currentUser") == null){
            resp.sendRedirect(req.getContextPath() + "/pages/login.jsp");
        } else {
            getServletContext().getRequestDispatcher("/pages/settings.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("name");
        String secondName = req.getParameter("surname");
        String userName = req.getParameter("username");
        String photo = req.getParameter("photo");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (req.getSession().getAttribute("currentUser") == null){
            resp.sendRedirect(req.getContextPath() + "/pages/login.jsp");
        } else {
        }
    }
}
