package by.tms.instaclone22onl.filter;

import by.tms.instaclone22onl.web.servlet.LocalizationServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

@WebFilter(urlPatterns = {"/login", "/register", "/settings", "/pages/login.jsp", "/pages/register.jsp", "/pages/settings.jsp"})
public class LocalizationFilter extends HttpFilter {
    private InputStream  filePathEn = getClass().getClassLoader().getResourceAsStream("massage_eng.properties");
    private InputStream  filePathRu = getClass().getClassLoader().getResourceAsStream("massage_rus.properties");
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        Locale locale = null;
        Cookie[] cookies = req.getCookies();

        if (cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("locale")){
                    String language = cookie.getValue();
                    locale = new Locale(language);
                    break;
                }
            }
        }

        if (locale == null){
            locale = new Locale("en");
        }

        String language = locale.getLanguage();
        Properties properties = new Properties();
        InputStream  filePath;

        if ("ru".equals(language)) {
            filePath = filePathRu;
        } else {
            filePath = filePathEn;
        }

        try {
            properties.load(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("properties", properties);
        chain.doFilter(req, res);
    }
}
