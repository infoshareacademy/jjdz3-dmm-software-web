package com.dmmsoft.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by milo on 19.05.17.
 */


@WebServlet(urlPatterns = "/auth/userview/usermenu")
public class UserMenuServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMenuServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("authenticatedUser");
        LOGGER.info("user from session:{}", user.getLogin());
        resp.sendRedirect("usermenu.jsp");
    }
}