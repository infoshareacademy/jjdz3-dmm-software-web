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

            LOGGER.info("User Menagement");
            req.getRequestDispatcher("../adminview/userManagement.jsp").forward(req, resp);

        }

}
