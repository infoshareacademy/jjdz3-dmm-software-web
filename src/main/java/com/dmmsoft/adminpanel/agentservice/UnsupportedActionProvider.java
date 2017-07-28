package com.dmmsoft.adminpanel.agentservice;

import com.dmmsoft.adminpanel.trigger.ITriggerable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by milo on 25.07.17.
 */
public class UnsupportedActionProvider implements ITriggerable {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnsupportedActionProvider.class);

    @Override
    public void executeAction() {
        LOGGER.error("Unsupported task type!");
    }
}

