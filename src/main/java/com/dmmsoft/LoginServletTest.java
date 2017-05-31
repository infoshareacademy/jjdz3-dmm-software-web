package com.dmmsoft;

import com.dmmsoft.user.IUserService;
import com.dmmsoft.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by milo on 26.05.17.
 */

@WebServlet(urlPatterns = "/login")
public class LoginServletTest extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServletTest.class);

    private final String TESTLOGIN="user1@user1.com";

    @Inject
    private IUserService userStorage;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /* MANUAL TESTING
        Note! Now user authentication is mocked:

        Temporary db user data are inserted to empty db by accesing servlet:
        http://localhost:8080/financial-app/manualtest

        http://localhost:8080/financial-app/login (user standard role - default user1@user1.com)

        Always check db user table to be sure of current user login
        */

        User user = userStorage.getUserByEmail(TESTLOGIN).get(0);
        HttpSession session = req.getSession();
        session.setAttribute("authenticatedUser", user);

        LOGGER.info("UserAuthenticated: Id:" + user.getId() + " login:" + user.getLogin()
                + "Role(isAdmin): " + user.getAdmin());

        if (!user.getAdmin()) {
            LOGGER.info("User view redirection: isAdmin:" + user.getAdmin());
            req.getRequestDispatcher("usermenu").forward(req, resp);
        } else {
            LOGGER.info("User view redirection: isAdmin:" + user.getAdmin());
            req.getRequestDispatcher("../adminview/adminmenu.jsp").forward(req, resp);
        }

    }

}
