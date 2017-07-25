package com.dmmsoft.api.client;

import org.codehaus.jackson.annotate.JsonProperty;
import com.dmmsoft.user.report.UserActivity;

import java.util.List;

/**
 * Created by milo on 25.07.17.
 */
public class RestResponse {


    private List<UserActivityDetails> userActivityDetails;

    public List<UserActivityDetails> getUserActivityDetails() {
        return userActivityDetails;
    }

    public void setUserActivityDetails(List<UserActivityDetails> userActivityDetails) {
        this.userActivityDetails = userActivityDetails;
    }


}