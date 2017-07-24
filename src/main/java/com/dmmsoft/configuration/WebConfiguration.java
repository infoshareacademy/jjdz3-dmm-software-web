package com.dmmsoft.configuration;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.net.URI;

/**
 * Created by milo on 25.07.17.
 */


public class WebConfiguration {

    private String masterModeAPIServiceURI;
    private String slaveModeAPIServiceURI;

    public WebConfiguration getWebConfiguration() {

        // TODO Get config from file

        this.masterModeAPIServiceURI = "http://localhost:8080/financial-app/api";
        this.slaveModeAPIServiceURI = "http://192.168.1.100:8080/financial-app/api";
        return this;
    }
}



