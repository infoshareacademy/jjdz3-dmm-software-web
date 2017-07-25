package com.dmmsoft.api.client;

import com.dmmsoft.configuration.AppMode;
import com.dmmsoft.user.report.UserActivity;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import java.util.List;

/**
 * Created by milo on 25.07.17.
 */
public class ReportClient {

    public List<UserActivity> getAllUserActivity() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new AppMode()
                .getWebConfiguration()
                .getSlaveModeAPIServiceTargetURI()
                + "/users/activity");

        Response response = target.request().get();
        Container container = response.readEntity(Container.class);
        response.close();
        return container.getRestResponse().getUserActivityList();
    }


}
