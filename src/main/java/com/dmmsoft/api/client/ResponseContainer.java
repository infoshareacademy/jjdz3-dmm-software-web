package com.dmmsoft.api.client;

import com.dmmsoft.user.report.UserActivity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by milo on 25.07.17.
 */

public class ResponseContainer {

    @JsonProperty("userActivities")
    private List<UserActivity> userActivities;

    public List<UserActivity> getUserActivities() {
        return userActivities;
    }

    public void setUserActivities(List<UserActivity> userActivities) {
        this.userActivities = userActivities;
    }
}