package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnfollowServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User follower=(User)req.getSession().getAttribute ("follower");
        User followee = (User)req.getSession().getAttribute("followee");
        if (follower!= null && followee!=null){
            userService.unfollow(follower,followee);
            resp.getWriter().write("You have  unfollowed " + followee.getId());
        }else {
            resp.getWriter().write("Follower is not found");
        }

    }
}

 /* User follower = (User) req.getSession().getAttribute("follower");
  int followee = Integer.parseInt(req.getParameter("followee"));
  userService.unfollow(follower,followee );
  resp.sendRedirect("/user/?id =" + followee);
   }
  }*/