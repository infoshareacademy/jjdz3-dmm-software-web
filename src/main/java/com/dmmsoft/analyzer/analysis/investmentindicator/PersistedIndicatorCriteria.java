package com.dmmsoft.analyzer.analysis.investmentindicator;

import com.dmmsoft.app.analyzer.analyses.indicator.IndicatorCriteria;
import com.dmmsoft.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by milo on 20.06.17.
 */

@Entity
public class PersistedIndicatorCriteria extends IndicatorCriteria {

    @ManyToOne
    private User user;
    private LocalDateTime creationDateTime;
    private LocalDateTime lastUpdateDateTime;
    private String userCustomName;

    public String getUserCustomName() {
        return userCustomName;
    }

    public void setUserCustomName(String userCustomName) {
        this.userCustomName = userCustomName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }


    public LocalDateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }


    public PersistedIndicatorCriteria() {

    }


    @PrePersist
    private void onCreate() {
        creationDateTime = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        lastUpdateDateTime = LocalDateTime.now();
    }


}
