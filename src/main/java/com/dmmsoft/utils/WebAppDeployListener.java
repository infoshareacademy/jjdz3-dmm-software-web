package com.dmmsoft.utils;


import com.dmmsoft.user.IUserService;
import com.dmmsoft.webconfiguration.AppMode;
import com.dmmsoft.container.IModelContainerService;
import com.dmmsoft.container.ModelContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.util.TimeZone;

/**
 * Created by milo on 02.06.17.
 */

@WebListener
public final class WebAppDeployListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebAppDeployListener.class);
    private static final String DEFAULT_TIMEZONE = "UTC";

    @Inject
    private IModelContainerService container;
    @Inject
    private IUserService userService;

    @Inject
    AppMode appMode;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOGGER.info("Application context Initialized.");
        this.setApplicationDefaultTimeZone(DEFAULT_TIMEZONE);
        servletContextEvent.getServletContext().setAttribute("appMode", appMode);

        if (!appMode.isSlave()) {
            this.loadModelData();
        }
        userService.addDefaultAdminUser();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.info("Application Servlet Context Destroyed.");
    }

    private void loadModelData() {
        try {
            LOGGER.info("Data model CSV files loading initialized...");

            new ModelContainer().updateModelFileResources();

            container.getMainContainer();

            LOGGER.info("Currencies items:{} Funds items:{}",
                    container.getMainContainer().getCurrenciesCount(),
                    container.getMainContainer().getFundsCount());
        } catch (RuntimeException ex) {
            LOGGER.error("FATAL ERROR: Failed to load data model CSV files! {}", ex.getMessage());
        }
    }

    private void setApplicationDefaultTimeZone(String timeZone) {
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
        LOGGER.info("Default application timezone set to: {}", TimeZone.getDefault());
    }
}
