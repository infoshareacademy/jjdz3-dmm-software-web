package com.dmmsoft.adminpanel.email;


import com.dmmsoft.adminpanel.schedule.Agent;
import com.dmmsoft.adminpanel.schedule.ITaskService;
import com.dmmsoft.adminpanel.trigger.AgentTrigger;
import com.dmmsoft.analyzer.IFavouriteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.dmmsoft.ConstantsProvider.CONTENT_WRAPPER;


/**
 * Created by milo on 07.07.17.
 */
@WebServlet(urlPatterns = "/auth/adminview/emailsender")
public class EmailServlet extends HttpServlet {

    @Inject
    private IFavouriteService favouriteService;
    @Inject
    private ITaskService taskService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServlet.class);
    private static final long TRIGGER_DELAY = 0;
    private static final long TRIGGER_TIMESPAN = 5;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute(CONTENT_WRAPPER, taskService.getAllTasks());
        req.getRequestDispatcher("../adminview/reportingService.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Agent actionPovider = new Agent(taskService, favouriteService);
            new AgentTrigger().startAction(actionPovider, TRIGGER_DELAY, TRIGGER_TIMESPAN, TimeUnit.SECONDS);

        } catch (RuntimeException ex) {

            LOGGER.error("Failed to send Email: {}", ex.getStackTrace());
        }
        req.setAttribute(CONTENT_WRAPPER, taskService.getAllTasks());
        req.getRequestDispatcher("../adminview/reportingService.jsp").forward(req, resp);
    }
}


