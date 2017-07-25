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
        this.isSlave = false;

        // TODO Get application mode from config file
/*        String externalPath = new AppConfigurationProvider()
                .getConfiguration()
                .getExternalResourceFilePath();
        Path appModePath = Paths.get(externalPath, "appmode.json");

        String content = new ConfigFileReader(appModePath).getFileAsString();
        this.isSlave = getProperties(content).isSlave;*/
    }

    private AppMode getProperties(String jsonString) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, AppMode.class);
        } catch (IOException e) {
            throw new IOException();
        }
    }

}
