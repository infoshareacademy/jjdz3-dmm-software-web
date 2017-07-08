package com.dmmsoft.adminpanel.emailsender;

/**
 * Created by milo on 08.07.17.
 */

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;

public class TimeTrigger {


    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    public void startAction(ITrigerable actionProvider, long delay, long period, TimeUnit timeUnit) {

        final Runnable trigger = new Runnable() {
            public void run() {
                actionProvider.executeAction();
            }
        };
        final ScheduledFuture<?> actionHandle =
                scheduler.scheduleAtFixedRate(trigger, delay, period, timeUnit);

        scheduler.schedule(new Runnable() {

            public void run() {
                actionHandle.cancel(true);
            }
        }, 60 * 60, SECONDS);
    }
}


