package com.dmmsoft;

import com.dmmsoft.user.Security;
import com.dmmsoft.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by milo on 19.05.17.
 */


@WebServlet(urlPatterns = "/usermenu")
public class UserMenuServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMenuServlet.class);
    private final boolean isADMIN_VIEW = false;

    @Inject
    private Security security;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        security.checkRequest(req, resp, isADMIN_VIEW);

        User user = (User) req.getSession().getAttribute("authenticatedUser");
        LOGGER.info("user from session:{}", user.getLogin());
        resp.sendRedirect("userview/usermenu.jsp");
    }
}
