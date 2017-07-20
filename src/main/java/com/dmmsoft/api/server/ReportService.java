package com.dmmsoft.api.server;

import com.dmmsoft.user.User;
import com.dmmsoft.user.report.IUserActivityService;
import com.dmmsoft.user.report.UserActivity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * Created by milo on 20.07.17.
 */
@Path("/")
public class ReportService {

@Inject
private IUserActivityService userActivityService;

    @GET
    @Path("/users/activity")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLoginForm() {

       List<UserActivity> userActivityList= userActivityService.getAllUserActivity();
       return Response.ok(userActivityList).build();
    }

}
