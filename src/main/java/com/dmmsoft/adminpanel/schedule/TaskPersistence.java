package com.dmmsoft.adminpanel.schedule;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by milo on 08.07.17.
 */

@Default
public class TaskPersistence implements ITaskService  {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks  = em.createQuery("select m from Task m", Task.class).getResultList();
        return tasks;
    }

    @Override
    public Task getTaskbyId(long taskId) {
         return em.find(Task.class, taskId);
    }

    @Override
    @Transactional
    public void updateTask(Task task){
        Task t = em.find(Task.class, task.getId());
             t.setTaskName(task.getTaskName());
             t.setStartDate(task.getStartDate());
             t.setEndDate(task.getEndDate());
             t.setStartDelay(task.getStartDelay());
             t.setTimeSpan(task.getTimeSpan());
             t.setActive(task.isActive());
             t.setTaskTypeName(task.getTaskTypeName());
             em.merge(t);
    }

    @Override
    @Transactional
    public void AddTask(Task task) {
        em.persist(task);
    }

    @Override
    public List<String> getAllAvaliableTaskTypeNames() {
        List<String> avliableTasks = new ArrayList<>();
        avliableTasks.add("EMAIL_SENDING");
        avliableTasks.add("MAIN_CONTAINER_UPDATING");
        avliableTasks.add("API_REPORT_DATA_UPDATING");
        return avliableTasks;
    }
}
