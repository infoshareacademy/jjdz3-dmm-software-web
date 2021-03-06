package com.dmmsoft.adminpanel.trigger;

import com.dmmsoft.adminpanel.agentservice.Agent;
import com.dmmsoft.adminpanel.agentservice.ITaskService;
import com.dmmsoft.analyzer.IFavouriteService;
import com.dmmsoft.user.report.IUserActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

/**
 * Created by milo on 24.07.17.
 */


@Singleton
public class AgentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgentController.class);
    private static final long TRIGGER_DELAY = 0;
    private static final long TRIGGER_TIMESPAN = 5;

    @Inject
    IFavouriteService favouriteService;
    @Inject
    ITaskService taskService;
    @Inject
    IUserActivityService userActivityService;


    private Agent agent;
    private boolean isStarted;
    private Trigger trigger;

    public AgentController() {
        isStarted = false;
    }

    public boolean isStarted() {
        return isStarted;
    }

    @PostConstruct
    private void onPostConstruct() {
        agent = new Agent(taskService, favouriteService, userActivityService);
        trigger = new Trigger(agent, TRIGGER_DELAY, TRIGGER_TIMESPAN, TimeUnit.SECONDS);
    }

    public void switchAgent(){

        if(isStarted){
            agent.forceShutDownAllTriggeredTasks();
            trigger.killAction();
            isStarted = false;
        }
        else if(isStarted==false){
            trigger.startAction();
            isStarted = true;
        }
        else {
            throw new RuntimeException("Agent controller failed to switch Agent state");
        }
    }






}
