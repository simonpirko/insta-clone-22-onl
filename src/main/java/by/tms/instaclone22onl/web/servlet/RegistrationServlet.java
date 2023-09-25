package by.tms.instaclone22onl.web.servlet;

import by.tms.instaclone22onl.model.Country;
import by.tms.instaclone22onl.model.User;
import by.tms.instaclone22onl.service.CountryService;
import by.tms.instaclone22onl.service.UserService;

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
import java.util.Optional;

@WebServlet("/register")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class RegistrationServlet extends HttpServlet {
    private final static String NAME = "name";
    private final static String SURNAME = "surname";
    private final static String USERNAME = "username";
    private final static String EMAIL = "email";
    private final static String PASSWORD = "password";
    private final static String COUNTRY = "country";
    private final static String PHOTO = "photo";

    private static final String USER_ALREADY_EXISTS = "This user already exists!";
    private static final String REGISTRATION_FAILED = "Registration failed. Check the entered data!";

    private static final String REG_PATH = "/pages/register.jsp";
    private static final String LOG_IN_PATH = "/login";

    private final UserService userService = UserService.getInstance();
    private final CountryService countryService = CountryService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Country> countryList = countryService.getAll();
        req.setAttribute("countries", countryList);
        getServletContext().getRequestDispatcher(REG_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream photoInputStream = req.getPart(PHOTO).getInputStream();
        String name = req.getParameter(NAME);
        String surname = req.getParameter(SURNAME);
        String username = req.getParameter(USERNAME);
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PASSWORD);
        Country country = countryService.getById(Integer.parseInt(req.getParameter(COUNTRY))).orElse(new Country());


        User user = User.builder()
                .setName(name)
                .setSurname(surname)
                .setUsername(username)
                .setEmail(email)
                .setPassword(password)
                .setCountry(country)
                .setPhoto(Base64.getEncoder().encodeToString(photoInputStream.readAllBytes()))
                .build();
        Optional<User> byUsername = userService.getUserByName(username);
        if (byUsername.isEmpty()) {
            UserService.getInstance().add(user);
            resp.sendRedirect(LOG_IN_PATH);
            return;
        } else {
            req.setAttribute("message", USER_ALREADY_EXISTS);
        }

        if (!byUsername.isPresent()) {
            req.setAttribute("errormessage", REGISTRATION_FAILED);
        }

        getServletContext().getRequestDispatcher(REG_PATH).forward(req, resp);

    }
}
