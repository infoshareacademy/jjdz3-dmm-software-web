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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public LocalDateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public void setLastUpdateDateTime(LocalDateTime lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
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
