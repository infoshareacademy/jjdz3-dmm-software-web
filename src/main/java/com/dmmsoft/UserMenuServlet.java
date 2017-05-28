package com.dmmsoft;

import com.dmmsoft.user.Security;
import com.dmmsoft.user.User;
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



@WebServlet(urlPatterns = "/usermenu")
public class UserMenuServlet extends HttpServlet{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMenuServlet.class);
    private Security security = new Security();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // security.checkRequest(req, resp);
        new Security().checkRequest(req, resp);

/*        if(req.getSession().getAttribute("authenticatedUser")==null){
            req.getRequestDispatcher("../accessdenied.jsp").forward(req,resp);
        }*/


       User user = (User) req.getSession().getAttribute("authenticatedUser");
       if(user.getAdmin()==true){
           req.getRequestDispatcher("manualtest").forward(req,resp);
       }

       LOGGER.info("user from session:"+user.getLogin());

       resp.sendRedirect("userview/usermenu.jsp");

    }
}
