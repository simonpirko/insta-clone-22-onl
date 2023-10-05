package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.model.Country;
import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.service.CountryService;
import by.tms.instaclone22onl.service.UserService;
import by.tms.instaclone22onl.storage.UserStorage.JdbcUserStorage;
import by.tms.instaclone22onl.storage.UserStorage.UserStorage;
import by.tms.instaclone22onl.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

@WebServlet("/settings")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class SettingsServlet extends HttpServlet {
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

        if (req.getSession().getAttribute("user") == null){
            resp.sendRedirect("/pages/login.jsp");
        } else {
        User user = (User) req.getSession().getAttribute("user");

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String username = req.getParameter("username");
        Country country = countryService.getById(Integer.parseInt(req.getParameter("country"))).orElse(new Country());
        InputStream photo = req.getPart("photo").getInputStream();
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setCountry(country);
        user.setPhoto(Base64.getEncoder().encodeToString(photo.readAllBytes()));
        user.setEmail(email);
        user.setPassword(password);

        storage.updateById(user);
        resp.sendRedirect("/pages/settings.jsp");

        if (!validator.validate(user)) {
               req.setAttribute("invalid data", 500);
        }
       }
    }
}
