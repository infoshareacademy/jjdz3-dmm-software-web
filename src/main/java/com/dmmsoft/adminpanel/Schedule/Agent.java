package com.dmmsoft.adminpanel.Schedule;

import com.dmmsoft.adminpanel.email.MailSender;
import com.dmmsoft.adminpanel.report.ReportComponents;
import com.dmmsoft.adminpanel.trigger.ITerminable;
import com.dmmsoft.adminpanel.trigger.ITriggerable;
import com.dmmsoft.adminpanel.trigger.TriggerProvider;
import com.dmmsoft.analyzer.IFavouriteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
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
    private List<ITerminable> triggerProviders = new ArrayList<>();

    public Agent(ITaskService taskService, IFavouriteService favouriteService) {
        this.taskService = taskService;
        this.favouriteService = favouriteService;
    }

    @Override
    public void executeAction() {
        LOGGER.debug("Agent execute action");
        this.doAgentJob();
    }

    private void doAgentJob() {
        LOGGER.info("Agent executes job start");
        this.checkActualTime();
        List<Task> userTasks = taskService.getAllTasks();

        LOGGER.info("number of tasks {}", userTasks.size());

        if (!triggeredTasks.isEmpty()) {
            LOGGER.info("Triggered tasks size: {} ",triggeredTasks.size());
        }


        for (Task item : userTasks) {
            if (item.isActive() && !hasTriggeredStatus(item) && isActualTask(item)) {
                this.startTask(item);
            } else {
                if (!triggeredTasks.isEmpty()) {
                    for (Task task : triggeredTasks) {
                        LOGGER.info("task to kill{} {} {}", isTaskToKill(task), task.getId(), task.getTaskName());

                        if(isTaskToKill(task))
                        {
                            LOGGER.info("Killing Task Trigger - TEST");
                            if (!triggerProviders.isEmpty())
                                for(ITerminable triggerProvider : triggerProviders)
                                {
                                    if(triggerProvider.getTaskId()==task.getId()){
                                        triggerProvider.killAction();
                                        LOGGER.info("Killing Task Trigger {} {}",
                                                task.getId(),
                                                task.getTaskName());
                                        triggeredTasks.remove(task);
                                        triggerProviders.remove(triggerProvider);
                                        LOGGER.info("Agent killing task trigger job end");
                                        if(triggeredTasks.isEmpty() || triggerProviders.isEmpty()){
                                            break;
                                        }
                                    }
                                }
                        }
                        if(triggeredTasks.isEmpty() || triggerProviders.isEmpty()){
                            break;
                        }
                    }
                }
            }
            LOGGER.info("Agent executes job end");
        }
    }

    private LocalDate checkActualTime() {
        return LocalDate.now();
    }

    private boolean isTaskToKill(Task task) {
       Task actualTask = taskService.getTaskbyId(task.getId());
        if (!actualTask.isActive() || !isActualTask(actualTask)) {
            return true;
        } else {
            return false;
        }
    }

    private void startTask(Task task) {
        ReportComponents reportComponents = new ReportComponents(favouriteService);
        MailSender actionPovider = new MailSender(reportComponents);
        TriggerProvider triggerProvider = new TriggerProvider(actionPovider, task.getStartDelay(), task.getTimeSpan(), TimeUnit.SECONDS);
        triggeredTasks.add(task);
        triggerProvider.setTriggeredTask(task);
        triggerProvider.startAction();

        triggerProviders.add(triggerProvider);

        LOGGER.info("Agent started Task: {} {}", task.getId(), task.getTaskName());
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



