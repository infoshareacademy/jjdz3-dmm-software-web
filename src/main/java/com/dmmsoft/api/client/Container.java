package com.dmmsoft.api.client;

import org.codehaus.jackson.annotate.JsonProperty;

public class Container {

    @JsonProperty("RestResponse")
    private RestResponse restResponse;

    public RestResponse getRestResponse() {
        return restResponse;
    }

    public void setRestResponse(RestResponse restResponse) {
        this.restResponse = restResponse;
    }
}
