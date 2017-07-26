package com.dmmsoft.webconfiguration;

import com.dmmsoft.app.appconfiguration.AppConfigurationProvider;
import com.dmmsoft.webconfiguration.utils.ConfigFileReader;
import com.fasterxml.jackson.databind.ObjectMapper;


import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by milo on 20.07.17.
 */

@Singleton
public class AppMode {

    private boolean isSlave;
    private WebConfigurationProvider webConfigurationProvider = new WebConfigurationProvider();

    public boolean isSlave() {
        return isSlave;
    }
    public AppMode() {
    }

    @PostConstruct
    public void onPostConstruct() {
        this.isSlave = webConfigurationProvider.getConfiguration().isSlave();
    }
}
