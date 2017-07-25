package com.dmmsoft.api.client;

import org.codehaus.jackson.annotate.JsonProperty;
import com.dmmsoft.user.report.UserActivity;

import java.util.List;

/**
 * Created by milo on 25.07.17.
 */
public class RestResponse {

@JsonProperty("result")
private List<UserActivity> userActivityList;

    public List<UserActivity> getUserActivityList() {
        return userActivityList;
    }

    public void setUserActivityList(List<UserActivity> userActivityList) {
        this.userActivityList = userActivityList;
    }
}
