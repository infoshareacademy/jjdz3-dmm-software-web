package com.dmmsoft.adminpanel.Schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by milo on 08.07.17.
 */

@Default
public class TaskPersistence implements ITaskService  {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskPersistence.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Task> getAllTasks() {
        return (List<Task>) em.createQuery("select m from Task m").getResultList();
    }

    @Override
    @Transactional
    public void AddTask(Task task) {
        em.persist(task);
    }

}
