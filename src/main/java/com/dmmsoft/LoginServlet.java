package com.dmmsoft;

import com.dmmsoft.analyzer.analysis.InvestmentRevenueServlet;
import com.dmmsoft.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

@WebServlet(urlPatterns = "/loginservlet")
public class LoginServlet extends HttpServlet {

private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);
long USER_ID = 1;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // TODO
        // 1 User G+ Authentication
        // 2 try to fetch user object from db:
        //   - new user -redirect to add user
        //   - user - get user object form db

        User user = new User();
        user.setId(USER_ID);
        user.setAdmin(false);
        user.setLogin("user@userdomain.com");
        HttpSession session = req.getSession();
        session.setAttribute("authenticatedUser",user);

        LOGGER.info("UserAuthenticated: Id:"+user.getId()+" login:"+user.getLogin()
                +"Role(isAdmin): " +user.getAdmin());

        if (user.getAdmin()==false)
        {
            LOGGER.info("");
            //resp.sendRedirect("usermenu");

            req.getRequestDispatcher("usermenu").forward(req,resp);
        }



    }


}
