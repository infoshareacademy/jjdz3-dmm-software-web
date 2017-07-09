package com.dmmsoft.adminpanel.Schedule;

import com.dmmsoft.adminpanel.email.EmailServlet;
import com.dmmsoft.adminpanel.email.MailSender;
import com.dmmsoft.adminpanel.report.ReportComponents;
import com.dmmsoft.adminpanel.trigger.ITriggerable;
import com.dmmsoft.adminpanel.trigger.TriggerProvider;
import com.dmmsoft.analyzer.IFavouriteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * Created by milo on 09.07.17.
 */

public class Agent implements ITriggerable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Agent.class);

    private ITaskService taskService;
    private IFavouriteService favouriteService;
    private List<Task> triggeredTasks = new ArrayList<>();
    private List<TriggerProvider> triggerProviders = new ArrayList<>();
    private boolean isAgentActive;

    public boolean isAgentActive() {
        return isAgentActive;
    }

    public void setAgentActive(boolean agentActive) {
        isAgentActive = agentActive;
    }

    public Agent(ITaskService taskService, IFavouriteService favouriteService) {
        this.taskService = taskService;
        this.favouriteService = favouriteService;
    }


    @Override
    public void executeAction() {
        // check actual time
        // take Task list
        // evaluate tasks to trigger:
        // -if task is Active
        // -if actualtime task is in time range
        // -if task was actually triggered
        // run current task and put to treggeredTasks
        //

        LOGGER.info("Agent lives");

        this.doAgentJob();
    }

    private void doAgentJob() {
        LOGGER.info("Agent executes job start");
        this.checkActualTime();
        List<Task> userTasks = taskService.getAllTasks();

        LOGGER.info("number of tasks {}", userTasks.size());

        for (Task item : userTasks) {
            if ((!item.isActive() || !isActualTask(item))
                    && hasTriggeredStatus(item)) {

                if (triggeredTasks.get(0) != null) {
                    LOGGER.info("kill action of: {}{}", triggeredTasks.get(0).getTaskName(), triggeredTasks.get(0).getId());
                }
                // TODO KILL ACTION
            }
            if (item.isActive() && !hasTriggeredStatus(item) && isActualTask(item)) {
                triggeredTasks.add(item);

                ReportComponents reportComponents = new ReportComponents(favouriteService);
                MailSender actionPovider = new MailSender(reportComponents);
                TriggerProvider triggerProvider =  new TriggerProvider(actionPovider,item.getStartDelay(),item.getTimeSpan(),TimeUnit.SECONDS);
                triggerProvider.startAction();

                LOGGER.info("Agent started Task: {} {}", item.getId(), item.getTaskName());

                // triggerProviders.add(triggerProvider); // use this
            }
        }
        LOGGER.info("Agent executes job end");
    }

    private LocalDateTime checkActualTime() {
        return LocalDateTime.now();
    }

    private boolean hasTriggeredStatus(Task task) {
        LOGGER.info("number of triggered tasks:{} ", triggeredTasks.size());
        LOGGER.info("checked task:{} ", task.getId());

        Predicate<Task> hasItem = x -> Objects.equals(x.getId(), task.getId());

        Optional<Task> any = triggeredTasks.stream()
                .filter(hasItem)
                .findAny();

        if (any.isPresent()) {
            LOGGER.info("has triggered status {}", task.getId());
            return true;
        } else {
            LOGGER.info("has not treiggered status {}", task.getId());
            return false;
        }
    }

    private boolean isActualTask(Task task) {
        boolean isActual = task.getStartDate().isBefore(this.checkActualTime())
                && task.getEndDate().isAfter(this.checkActualTime());

        LOGGER.info("Actual:{}, taskId:{}", isActual, task.getId());
        return isActual;
    }


}



