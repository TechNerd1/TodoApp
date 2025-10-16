package com.jogwheel.todolistproject.service;

import com.jogwheel.todolistproject.dto.request.CreateTaskRequest;
import com.jogwheel.todolistproject.dto.request.UpdateTaskRequest;
import com.jogwheel.todolistproject.dto.response.TaskResponse;
import com.jogwheel.todolistproject.entity.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskResponse createTask(CreateTaskRequest request);
    TaskResponse updateTask(UUID id, UpdateTaskRequest request);
    void deleteTask(UUID id);
    TaskResponse getTaskById(UUID id);
    List<TaskResponse> getTasksByTaskListId(UUID taskListId);
}
