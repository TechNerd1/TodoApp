package com.jogwheel.todolistproject.service;

import com.jogwheel.todolistproject.entity.TaskList;

import java.util.List;
import java.util.UUID;

public interface TaskListService {
    TaskList getTaskListById(UUID id);
    List<TaskList> getTaskLists();
    TaskList createTaskList(TaskList taskList);
    TaskList updateTaskList(TaskList taskList);
    void deleteTaskListById(UUID id);

}
