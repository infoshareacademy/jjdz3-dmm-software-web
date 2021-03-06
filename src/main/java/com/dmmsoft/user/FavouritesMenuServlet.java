package com.dmmsoft.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static com.dmmsoft.utils.ConstantsProvider.AUTH_USER;
/**
 * Created by milo on 19.05.17.
 */

@WebServlet(urlPatterns = "/auth/userview/favouritesmenu")
public class FavouritesMenuServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMenuServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute(AUTH_USER);
        resp.sendRedirect("favouritesMenu.jsp");
        LOGGER.info("user from session:{}", user.getLogin());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute(AUTH_USER);
        resp.sendRedirect("favouritesMenu.jsp");
        LOGGER.info("user from session:{}", user.getLogin());
    }
}