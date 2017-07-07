package com.dmmsoft.adminpanel.emailsender;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by milo on 07.07.17.
 */
@WebServlet(urlPatterns = "/auth/adminview/emailSender")
public class EmailSenderServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(com.dmmsoft
            .adminpanel
            .AppSettingsServlet
            .class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {

            MailSender sender = new MailSender();
            sender.sendEmail();

             /*
                req.setAttribute(...);
                req.setAttribute(...);*/

        } catch (RuntimeException ex) {

            LOGGER.error("Failed to send Email{}", ex.getMessage());
        }

        req.getRequestDispatcher("../adminview/appSettings.jsp").forward(req, resp);
    }
}


