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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.dmmsoft.utils.ConstantsProvider.CONTENT_WRAPPER;
import static com.dmmsoft.utils.ConstantsProvider.DATE_PATTERN;
import static com.dmmsoft.utils.ConstantsProvider.TASK_NAME;
import static com.dmmsoft.utils.ConstantsProvider.START_DATE;
import static com.dmmsoft.utils.ConstantsProvider.END_DATE;
import static com.dmmsoft.utils.ConstantsProvider.TASK_START_DELAY;
import static com.dmmsoft.utils.ConstantsProvider.TASK_TIMESPAN;
import static com.dmmsoft.utils.ConstantsProvider.TASK_ISACTIVE;

/**
 * Created by milo on 08.07.17.
 */


@WebServlet(urlPatterns = "/auth/adminview/task")
public class TaskServlet extends HttpServlet {

    @Inject
    private ITaskService taskService;
    @Inject
    IFavouriteService favouriteService;

    @Inject
    private AgentController agentController;
    private static final String AGENT_IS_STARTED = "agentIsStarted";
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServlet.class);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String editId = req.getParameter("id");
        long Id = Long.parseLong(editId);
        Task task = taskService.getTaskbyId(Id);

        req.setAttribute("taskNames", taskService.getAllAvaliableTaskTypeNames());
        req.setAttribute(CONTENT_WRAPPER, task);
        req.setAttribute(AGENT_IS_STARTED, agentController.isStarted());
        req.getRequestDispatcher("../adminview/taskview.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String taskName = req.getParameter(TASK_NAME);
        String taskTypeName = req.getParameter("taskTypeName");

        String startDate = req.getParameter(START_DATE);
        String endDate = req.getParameter(END_DATE);
        String startDelay = req.getParameter(TASK_START_DELAY);
        String timeSpan = req.getParameter(TASK_TIMESPAN);
        boolean isActive = req.getParameter(TASK_ISACTIVE) != null;

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
        task.setTaskTypeName(taskTypeName);


        if (id.isPresent()) {
            taskService.updateTask(task);
        } else {
            taskService.AddTask(task);
        }

        req.setAttribute(AGENT_IS_STARTED, agentController.isStarted());
        req.setAttribute(CONTENT_WRAPPER, taskService.getAllTasks());
        req.setAttribute("taskNames", taskService.getAllAvaliableTaskTypeNames());
        req.getRequestDispatcher("../adminview/reportingService.jsp").forward(req, resp);
    }
}
