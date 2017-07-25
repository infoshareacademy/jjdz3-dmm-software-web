package com.dmmsoft.configuration;

/**
 * Created by milo on 25.07.17.
 */


public class WebConfiguration {



    private String masterModeAPIServiceURI;
    private String slaveModeAPIServiceTargetURI;

    public String getMasterModeAPIServiceURI() {
        return masterModeAPIServiceURI;
    }

    public String getSlaveModeAPIServiceTargetURI() {
        return slaveModeAPIServiceTargetURI;
    }

    public WebConfiguration getWebConfiguration() {

        // TODO Get config from file

        this.masterModeAPIServiceURI = "http://localhost:8080/financial-app/api";
        this.slaveModeAPIServiceTargetURI = "http://192.168.1.104:8080/financial-app/api";
        return this;
    }
}



