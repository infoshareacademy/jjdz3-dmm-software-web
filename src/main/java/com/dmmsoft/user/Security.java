package com.dmmsoft.user;


import com.dmmsoft.analyzer.FavouriteServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by milo on 28.05.17.
 */
public class Security {

    private static final Logger LOGGER = LoggerFactory.getLogger(Security.class);

    public void checkRequest(HttpServletRequest req, HttpServletResponse resp, boolean isAdminView) throws ServletException, IOException {

        if (req.getSession().getAttribute("authenticatedUser") == null) {
            resp.sendRedirect("../accessdenied.jsp");
            LOGGER.warn("Not authenticated user session open attempt!");
            throw new SecurityException();
        }

        User user = (User) req.getSession().getAttribute("authenticatedUser");
        if (user.getAdmin() != isAdminView) {
            LOGGER.warn("Authenticated user not permitted view access attempt!");
            resp.sendRedirect("../accessdenied.jsp"); // TODO: redirect to Admin menu servlet (not implemented yet)

        }
    }
}
