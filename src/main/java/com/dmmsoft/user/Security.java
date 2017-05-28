package com.dmmsoft.user;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by milo on 28.05.17.
 */
public class Security {

   public void checkRequest(HttpServletRequest req,  HttpServletResponse resp) throws ServletException, IOException{
        if(req.getSession().getAttribute("authenticatedUser")==null){
            resp.sendRedirect("../accessdenied.jsp");
            throw new SecurityException();
        }
    }

}
