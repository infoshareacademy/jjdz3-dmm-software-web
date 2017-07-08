package com.dmmsoft.adminpanel.Schedule;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.dmmsoft.ConstantsProvider.CONTENT_WRAPPER;

/**
 * Created by milo on 08.07.17.
 */

@WebServlet(urlPatterns = "/auth/adminview/task")
public class TaskServlet extends HttpServlet {

    @Inject
    private ITaskService taskService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String editId = req.getParameter("id");
        long Id = Long.parseLong(editId);

        Task task = new Task();

        task.setId(Id);
        task.setTaskName("task edited by Id");
        task.setActive(true);
        task.setTimeSpan(60);

        req.setAttribute(CONTENT_WRAPPER,task);
        req.getRequestDispatcher("../adminview/taskview.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // TODO optional if present update if !present add


       String isActive = req.getParameter("isActive");
       String taskName = req.getParameter("taskName");


        Task task = new Task();
        task.setTaskName(taskName);
        task.setActive(true);
        task.setTimeSpan(60);

        taskService.AddTask(task);

        req.setAttribute(CONTENT_WRAPPER, taskService.getAllTasks());
        req.getRequestDispatcher("../adminview/reportingService.jsp").forward(req, resp);
    }
}
