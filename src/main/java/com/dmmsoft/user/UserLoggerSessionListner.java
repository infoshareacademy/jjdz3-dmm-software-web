package com.dmmsoft.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.ZoneOffset.UTC;

/**
 * Created by milo on 26.05.17.
 */

@WebListener
public class UserLoggerSessionListner implements HttpSessionListener, HttpSessionAttributeListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoggerSessionListner.class);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        if (httpSessionBindingEvent.getSession().getAttribute("authenticatedUser") != null) {
            User user = (User) httpSessionBindingEvent.getSession().getAttribute("authenticatedUser");
            LOGGER.info("User added to session (login):{}", user.getLogin());
        } else {
            LOGGER.warn("Not authenticated user session opening attempt! {}",
                    httpSessionBindingEvent
                            .getSession()
                            .getServletContext()
                            .getContextPath());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

        Instant instant = Instant.ofEpochMilli(httpSessionEvent.getSession().getCreationTime());
        LocalDateTime sessionCreated = LocalDateTime.ofInstant(instant, UTC);
        LOGGER.info("SessionId:{} Created:{}  ", httpSessionEvent.getSession().getId(), sessionCreated);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        LOGGER.info("Session Distroyed! SessionId:{}", httpSessionEvent.getSession().getId());
    }
}
