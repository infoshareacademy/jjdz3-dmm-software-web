package com.dmmsoft.user.report;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

/**
 * Created by milo on 15.07.17.
 */

@Entity
public class UserActivity {

    @Id
    @GeneratedValue
    private long Id;
    private String login;
    private String activityName;
    private String sessionId;
    private LocalDateTime activityDateTime;


    public UserActivity() {
    }

    @PrePersist
    void onCreate(){
        activityDateTime = LocalDateTime.now();
    }

    public UserActivity(String login, String activityName, String sessionId) {
        this.login = login;
        this.activityName = activityName;
        this.sessionId = sessionId;
    }

    public long getId() {
        return Id;
    }

    public String getLogin() {
        return login;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public LocalDateTime getActivityDateTime() {
        return activityDateTime;
    }
}
