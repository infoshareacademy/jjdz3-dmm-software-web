package com.dmmsoft.api.server;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Created by milo on 20.07.17.
 */
@Path("/")
public class ReportService {
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_HTML)
    public Response getLoginForm() {
        final String s = "api welcome content";
        return Response.ok(s).build();
    }


}
