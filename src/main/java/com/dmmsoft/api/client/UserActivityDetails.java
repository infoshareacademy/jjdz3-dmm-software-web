package com.dmmsoft.api.client;

import com.dmmsoft.user.report.UserActivity;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by milo on 25.07.17.
 */
public class UserActivityDetails {


    private UserActivity userActivity;

    @JsonProperty("id")
    private long id;

    public UserActivity getUserActivity() {
        return userActivity;
    }

    public void setUserActivity(UserActivity userActivity) {
        this.userActivity = userActivity;
    }

}
