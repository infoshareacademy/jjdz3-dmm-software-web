package com.dmmsoft.adminpanel.email;


import com.dmmsoft.adminpanel.trigger.TimeTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


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

           MailSender actionPovider = new MailSender();

           new TimeTrigger().startAction(actionPovider, 30, 10 , TimeUnit.SECONDS);

             /*
                req.setAttribute(...);
                req.setAttribute(...);*/

        } catch (RuntimeException ex) {

            LOGGER.error("Failed to send Email{}", ex.getMessage());
        }
        req.getRequestDispatcher("../adminview/appSettings.jsp").forward(req, resp);
    }
}


