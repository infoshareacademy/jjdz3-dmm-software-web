package com.dmmsoft.adminpanel.Schedule;

import java.util.List;

/**
 * Created by milo on 08.07.17.
 */
public interface ITaskService {

    List<Task> getAllTasks();

    void AddTask(Task task);


}
