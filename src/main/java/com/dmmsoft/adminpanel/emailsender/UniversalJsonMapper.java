package com.dmmsoft.adminpanel.emailsender;

import java.io.IOException;

import com.dmmsoft.app.appconfiguration.AppConfigurationProvider;
import com.dmmsoft.app.appconfiguration.exception.AppConfigurationProviderException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class UniversalJsonMapper {

    private final String jsonString;

    public UniversalJsonMapper(String jsonString)
    {
        this.jsonString = jsonString;
    }

    public SmtpProperties getProperties() throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, SmtpProperties.class);
        } catch (IOException e) {
            throw new IOException();
        }
    }


}
