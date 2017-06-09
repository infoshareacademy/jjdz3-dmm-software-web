package com.dmmsoft.user;

import com.dmmsoft.ConstantsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by milo on 09.06.17.
 */

@WebServlet(urlPatterns = "/auth/adminview/adminmenu")
public class AdminMenuServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminMenuServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute(ConstantsProvider.AUTH_USER);
        LOGGER.info("User from session:{}", user.getLogin());
        resp.sendRedirect("adminMenu.jsp");
    }
}


