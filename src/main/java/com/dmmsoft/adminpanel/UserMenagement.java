package com.dmmsoft.adminpanel;

import com.dmmsoft.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.dmmsoft.ConstantsProvider.ALL_USERS;

/**
 * Created by milo on 08.06.17.
 */

@WebServlet(urlPatterns = "/auth/adminview/usermanagement")
public class UserMenagement extends HttpServlet {

        private static final Logger LOGGER = LoggerFactory.getLogger(UserMenagement.class);

        @Inject
        IUserService userService;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            req.setAttribute(ALL_USERS, userService.getAllUsers());
            req.getRequestDispatcher("../adminview/userManagement.jsp").forward(req, resp);

            LOGGER.info("User Menagement module accessed.");
        }
}
