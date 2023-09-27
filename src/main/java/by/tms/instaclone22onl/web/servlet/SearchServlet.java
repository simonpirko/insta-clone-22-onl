package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

   private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/search.jsp").forward(req, resp);
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");


        List <User> usersByUsername = userService.getUsersWithUsernameContaining(username);

        if(!usersByUsername.isEmpty()){
//            User user = userByUsername.get();
//            usernamesList.add(userByUsername);
//
//
//            if(userByUsername.toString().contains(username)){
//                usernamesList.add(userByUsername);
//            }

            req.setAttribute("message", usersByUsername);

//resp.sendRedirect("/search");
        }
        else {
            req.setAttribute("message", "Username not found!");
        }
        getServletContext().getRequestDispatcher("/pages/search.jsp").forward(req, resp);
    }
}
