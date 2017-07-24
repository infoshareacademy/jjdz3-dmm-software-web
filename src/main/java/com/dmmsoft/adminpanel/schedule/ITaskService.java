package com.dmmsoft.adminpanel.schedule;

import java.util.List;

/**
 * Created by milo on 08.07.17.
 */
public interface ITaskService {

    List<Task> getAllTasks();

    void AddTask(Task task);
    List<String> getAllAvaliableTaskTypeNames();

    Task getTaskbyId(long id);

    void updateTask(Task task);
}
