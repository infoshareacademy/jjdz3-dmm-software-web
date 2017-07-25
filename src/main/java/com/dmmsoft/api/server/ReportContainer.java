package com.dmmsoft.api.server;

import com.dmmsoft.user.report.UserActivity;

import java.util.List;

/**
 * Created by milo on 25.07.17.
 */
public class ReportContainer {

   private List<UserActivity> userActivities;

    public List<UserActivity> getUserActivities() {
        return userActivities;
    }

    public void setUserActivities(List<UserActivity> userActivities) {
        this.userActivities = userActivities;
    }
}
