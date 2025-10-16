package com.jogwheel.todolistproject.service;

import com.jogwheel.todolistproject.entity.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    Task createTask(Task task, UUID taskListId);
    Task updateTask(Task task);
    void deleteTask(UUID id);
    Task getTaskById(UUID id);
    List<Task> getTasksByTaskListId(UUID taskListId);
}
