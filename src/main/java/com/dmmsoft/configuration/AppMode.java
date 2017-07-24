package com.dmmsoft.configuration;

import javax.annotation.PostConstruct;
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

    WebConfiguration webConfiguration;

    public WebConfiguration getWebConfiguration() {
        return webConfiguration;
    }

    public AppMode() {
    }

    @PostConstruct
    public void onPostConstruct() {

        // TODO Get application mode from config file
        webConfiguration = new WebConfiguration().getWebConfiguration();
        this.isSlave = false;
    }


}
