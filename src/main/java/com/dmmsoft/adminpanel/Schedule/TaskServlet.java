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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.dmmsoft.ConstantsProvider.CONTENT_WRAPPER;
import static com.dmmsoft.ConstantsProvider.DATE_PATTERN;

/**
 * Created by milo on 08.07.17.
 */

@WebServlet(urlPatterns = "/auth/adminview/task")
public class TaskServlet extends HttpServlet {

    @Inject
    private ITaskService taskService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServlet.class);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String editId = req.getParameter("id");
        long Id = Long.parseLong(editId);
        Task task = taskService.getTaskbyId(Id);

        req.setAttribute(CONTENT_WRAPPER,task);
        req.getRequestDispatcher("../adminview/taskview.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String taskName = req.getParameter("taskName");

        String startDate =req.getParameter("startDate");
        String endDate =req.getParameter("endDate");
        String startDelay = req.getParameter("startDelay");
        String timeSpan = req.getParameter("timeSpan");
        boolean isActive = req.getParameter("isActive") != null;

        Optional<Long> id = Optional.ofNullable(req.getParameter("id"))
                .map(String::trim)
                .filter(idString -> !idString.isEmpty())
                .map(Long::parseLong);

        Task task = id.map(Task::new)
                .orElseGet(Task::new);

        task.setTaskName(taskName);
        task.setStartDate(LocalDate.parse(startDate, formatter));
        task.setEndDate(LocalDate.parse(endDate, formatter));
        task.setStartDelay(Long.parseLong(startDelay));
        task.setTimeSpan(Long.parseLong(timeSpan));
        task.setActive(isActive);

        if(id.isPresent()){
            taskService.updateTask(task);
        }else {
            taskService.AddTask(task);
        }

        req.setAttribute(CONTENT_WRAPPER, taskService.getAllTasks());
        req.getRequestDispatcher("../adminview/reportingService.jsp").forward(req, resp);
    }
}
