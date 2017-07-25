package com.dmmsoft.configuration;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by milo on 20.07.17.
 */

@Singleton
public class AppMode {


    private boolean isSlave;
    public boolean isSlave() {
        return isSlave;
    }
    public void setSlave(boolean slave) {
        isSlave = slave;
    }

    public AppMode() {
    }

    @PostConstruct
    public void onPostConstruct() {
        // TODO Get application mode from config file
        this.isSlave = true;
    }

}
