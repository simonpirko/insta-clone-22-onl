package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.model.Country;
import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.service.CountryService;
import by.tms.instaclone22onl.service.UserService;
import by.tms.instaclone22onl.storage.UserStorage.JdbcUserStorage;
import by.tms.instaclone22onl.storage.UserStorage.UserStorage;
import by.tms.instaclone22onl.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@WebServlet("/settings")
public class SettingsServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    private final CountryService countryService = CountryService.getInstance();
    private final Validator validator = new Validator();
    private final UserStorage storage = JdbcUserStorage.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Country> countryList = countryService.getAll();
        req.setAttribute("countries", countryList);

        getServletContext().getRequestDispatcher("/pages/settings.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String username = req.getParameter("username");
        Country country = countryService.getById(Integer.parseInt(req.getParameter("country"))).orElse(new Country());
        InputStream photo = req.getPart("photo").getInputStream();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        int userId = Integer.parseInt(req.getParameter("id"));

        User user = User.builder()
                .setName(name)
                .setSurname(surname)
                .setUsername(username)
                .setCountry(country)
                .setPhoto(Base64.getEncoder().encodeToString(photo.readAllBytes()))
                .setEmail(email)
                .setPassword(password)
                .setId(userId)
                .build();


        Optional<User> byUsername = userService.getUserByName(username);

       if (byUsername.isEmpty()){
           UserService.getInstance().add(user);
           resp.sendRedirect("/pages/login.jsp");
        } else {
          try {
              storage.updateById(user);
              resp.sendRedirect("/pages/settings.jsp");
          } catch (IOException e) {
              throw new RuntimeException(e);
          }

           if (!validator.validate(user)) {
               req.setAttribute("invalid data", 500);
           }
       }
    }
}
