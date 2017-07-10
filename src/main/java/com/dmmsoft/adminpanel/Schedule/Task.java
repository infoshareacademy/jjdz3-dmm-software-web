package com.dmmsoft.adminpanel.Schedule;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by milo on 08.07.17.
 */
@Entity
public class Task {

    @Id
    @GeneratedValue
    private long id;
    private String taskName;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;
    private long startDelay;
    private long timeSpan;


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public long getStartDelay() {
        return startDelay;
    }

    public void setStartDelay(long startDelay) {
        this.startDelay = startDelay;
    }

    public long getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(long timeSpan) {
        this.timeSpan = timeSpan;
    }

    public Task() {
    }

    public Task(long id){
        this.id = id;
    }
}
