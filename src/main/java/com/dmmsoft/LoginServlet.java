package com.dmmsoft;

import com.dmmsoft.analyzer.analysis.InvestmentRevenueServlet;
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
public class LoginServlet extends HttpServlet {

private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);

private Long USER_ID;

@Inject
private IUserService userStorage;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // TODO
        // 1 User G+ Authentication
        // 2 try to fetch user object from db:
        //   - new user -redirect to add user
        //   - user - get user object form db

        // for Manual Tests:
        // Temporary user change before user authentication implementation :

        String id =req.getParameter("id");
        if(id==null || id.isEmpty()) {
            id = "1";
        }
        USER_ID = Long.parseLong(id);

        User user = userStorage.get(USER_ID);
        HttpSession session = req.getSession();
        session.setAttribute("authenticatedUser",user);

        LOGGER.info("UserAuthenticated: Id:"+user.getId()+" login:"+user.getLogin()
                +"Role(isAdmin): " +user.getAdmin());

        if (user.getAdmin()==false) {
            LOGGER.info("User view redirection: isAdmin:"+user.getAdmin());
            req.getRequestDispatcher("usermenu").forward(req,resp);
        }



    }


}
