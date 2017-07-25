package com.dmmsoft.api.server;

import com.dmmsoft.user.report.IUserActivityService;
import com.dmmsoft.user.report.UserActivity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    public Response getLoginForm() throws JsonProcessingException {

       List<UserActivity> userActivityList= userActivityService.getAllUserActivity();

       ObjectMapper mapper = new ObjectMapper();

       ReportContainer reportContainer= new ReportContainer();
       reportContainer.setUserActivities(userActivityList);

       String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(reportContainer);

       return Response.ok(result).build();
    }

}
