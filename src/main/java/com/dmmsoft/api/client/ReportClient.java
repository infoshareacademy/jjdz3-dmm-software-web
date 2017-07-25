package com.dmmsoft.api.client;

import com.dmmsoft.configuration.AppMode;
import com.dmmsoft.user.report.UserActivity;
import com.dmmsoft.user.report.UserActivityReportServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milo on 25.07.17.
 */
public class ReportClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportClient.class);

    public List<UserActivity> getAllUserActivity() {
        Client client = ClientBuilder.newClient();

/*        WebTarget target = client.target(new AppMode()
                .getWebConfiguration()
                .getSlaveModeAPIServiceTargetURI()
                + "/users/activity");*/
        WebTarget target = client.target("http://192.168.1.104:8080/financial-app/api/users/activity");


        LOGGER.info("taret uri: {}",target.getUri());

        Response response = target.request().get();

        //LOGGER.info("target get entity: {}", response.readEntity(String.class));

        RestResponse restResponse = response.readEntity(RestResponse.class);



        response.close();

        return restResponse.getUserActivities();
       // return new ArrayList<UserActivityDetails>();


    }
}
