package com.dmmsoft.webconfiguration;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by milo on 26.07.17.
 */
public class WebConfigurationProviderTest extends TestCase {

    @Test
    public void testGetConfiguration() throws Exception {
        WebConfigurationProvider configurationProvider = new WebConfigurationProvider();
        configurationProvider.getConfiguration();

        System.out.println("test value isSlave"+configurationProvider.getConfiguration().isSlave());
    }
}