package com.dmmsoft;

import com.dmmsoft.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Session;
import javax.servlet.http.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static java.time.ZoneOffset.UTC;

/**
 * Created by milo on 26.05.17.
 */
public class SessionListner implements HttpSessionListener, HttpSessionAttributeListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionListner.class);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public SessionListner() {
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
      User user = (User)httpSessionBindingEvent.getSession().getAttribute("authenticatedUser");
        LOGGER.info("User added to session (login): "+user.getLogin());
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
      LocalDateTime sessionCreated =  LocalDateTime.ofInstant(instant, UTC);
      LOGGER.info("Session Created: "+sessionCreated);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        LOGGER.info("Session Distroyed! ");
    }
}
