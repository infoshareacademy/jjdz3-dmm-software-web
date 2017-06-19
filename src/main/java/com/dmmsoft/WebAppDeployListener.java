package com.dmmsoft;

import com.dmmsoft.container.IModelContainerService;
import org.joda.time.tz.UTCProvider;
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
    IModelContainerService container;


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        setApplicationDefaultTimeZone(DEFAULT_TIMEZONE);
        loadCSVFiles();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.info("Application Servlet Context Destroyed.");
    }

    private void loadCSVFiles() {
        try {
            LOGGER.info("Application Deployed. Data model CSV files loading initialized...");

            container.getMainContainer();
            LOGGER.info("Currencies items:{} Funds items:{}",
                    container.getMainContainer().getCurrenciesCount(),
                    container.getMainContainer().getFundsCount());
            LOGGER.info("Application Deployed. Main Container initialized: CSV files successfully loaded.");
        } catch (RuntimeException ex) {
            LOGGER.error("FATAL ERROR: Failed to load data model CSV files! {}", ex.getMessage());
        }

    }

    private void setApplicationDefaultTimeZone(String timeZone) {
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
        LOGGER.info("Default application timezone set to: {}", TimeZone.getDefault());
    }

}
