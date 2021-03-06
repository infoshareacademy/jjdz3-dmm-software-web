package com.dmmsoft.adminpanel.agentservice;


import com.dmmsoft.adminpanel.trigger.AgentController;
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

import static com.dmmsoft.utils.ConstantsProvider.CONTENT_WRAPPER;


/**
 * Created by milo on 07.07.17.
 */
@WebServlet(urlPatterns = "/auth/adminview/emailsender")
public class AgentServlet extends HttpServlet {

    @Inject
    private IFavouriteService favouriteService;
    @Inject
    private ITaskService taskService;
    @Inject
    private AgentController agentController;


    private static final Logger LOGGER = LoggerFactory.getLogger(AgentServlet.class);
    private static final String AGENT_IS_STARTED="agentIsStarted";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute(CONTENT_WRAPPER, taskService.getAllTasks());
        req.setAttribute(AGENT_IS_STARTED, agentController.isStarted());
        req.setAttribute("taskNames", taskService.getAllAvaliableTaskTypeNames());
        req.getRequestDispatcher("../adminview/reportingService.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {

        agentController.switchAgent();

        } catch (RuntimeException ex) {

            LOGGER.error("Failed to send Email: {}", ex.getStackTrace());
        }

        req.setAttribute(AGENT_IS_STARTED, agentController.isStarted());
        req.setAttribute(CONTENT_WRAPPER, taskService.getAllTasks());
        req.setAttribute("taskNames", taskService.getAllAvaliableTaskTypeNames());
        req.getRequestDispatcher("../adminview/reportingService.jsp").forward(req, resp);
    }
}


