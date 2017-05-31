package com.dmmsoft;

import com.dmmsoft.loginwithgoogle.IdTokenVerifierAndParser;
import com.dmmsoft.user.IUserService;
import com.dmmsoft.user.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
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
 * Created by daniel on 29.05.17.
 */


@WebServlet(urlPatterns = "login2")
public class LoginServletGoogle extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServletTest.class);

    @Inject
    IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");

        try {
            String idToken = req.getParameter("id_token");
            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
            String name = (String) payLoad.get("name");
            String email = payLoad.getEmail();

            User user = userDBaseProcessing(email);
            HttpSession session = req.getSession(true);
            session.setAttribute("authenticatedUser", user);
            req.getServletContext();

            LOGGER.info("UserAuthenticated: Id:{} login:{} role:{}", user.getId(), user.getLogin(), user.getAdmin());

            userViewRedirection(user, req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private void userViewRedirection(User user, HttpServletRequest req, HttpServletResponse resp) {

        try {
            if (user.getAdmin() == false) {
                LOGGER.info("User view redirection: isAdmin:" + user.getAdmin());
                req.getRequestDispatcher("usermenu").forward(req, resp);
            } else {
                LOGGER.info("User view redirection: isAdmin:" + user.getAdmin());
                req.getRequestDispatcher("../adminview/adminmenu.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private User userDBaseProcessing(String userEmail) {

        if (userService.getUserByEmail(userEmail).isEmpty()) {
            User user = new User();
            user.setLogin(userEmail);
            user.setAdmin(false);
            userService.add(user);
            return user;
        } else {
            return userService.getUserByEmail(userEmail).stream()
                    .findAny()
                    .orElseThrow(NullPointerException::new);
        }
    }

}
