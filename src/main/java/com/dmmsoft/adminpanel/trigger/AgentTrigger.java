package com.dmmsoft.adminpanel.trigger;

/**
 * Created by milo on 09.07.17.
 */

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class AgentTrigger {

    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    public void startAction(ITriggerable actionProvider, long delay, long period, TimeUnit timeUnit) {

        final Runnable trigger = new Runnable() {
            public void run() {
                actionProvider.executeAction();
            }
        };
        final ScheduledFuture<?> actionHandle =
                scheduler.scheduleAtFixedRate(trigger, delay, period, timeUnit);

    }
}


