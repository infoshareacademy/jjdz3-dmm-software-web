package com.dmmsoft;

import com.dmmsoft.loginwithgoogle.IdTokenVerifierAndParser;
import com.dmmsoft.user.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

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
    @Override
    protected void doPost (HttpServletRequest req,
                           HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");

        try {
            String idToken = req.getParameter("id_token");
            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
            String name = (String) payLoad.get("name");
            String email = payLoad.getEmail();




            HttpSession session = req.getSession(true);
            session.setAttribute("userName", name);
            req.getServletContext();
            resp.sendRedirect("userview.jsp");




        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
