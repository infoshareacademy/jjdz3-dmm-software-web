package com.dmmsoft;

import com.dmmsoft.container.IDataContainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by milo on 02.06.17.
 */

@WebListener
public final class WebAppDeployListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebAppDeployListener.class);
    @Inject
    IDataContainerService container;


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        // initial csv data load to application memory
        container.getMainContainer();
        LOGGER.info("Application Deployed. Main Container initialized: CSV files loaded.");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.info("Application Servlet Context Destroyed.");

    }
}
