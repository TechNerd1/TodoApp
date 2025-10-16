package com.jogwheel.todolistproject.service;

import com.jogwheel.todolistproject.dto.request.CreateTaskListRequest;
import com.jogwheel.todolistproject.dto.request.CreateTaskRequest;
import com.jogwheel.todolistproject.dto.request.UpdateTaskListRequest;
import com.jogwheel.todolistproject.dto.response.TaskListResponse;

import java.util.List;
import java.util.UUID;

public interface TaskListService {
    TaskListResponse getTaskListById(UUID id);
    List<TaskListResponse> getTaskLists();
    TaskListResponse createTaskList(CreateTaskListRequest request);
    TaskListResponse updateTaskList(UUID id, UpdateTaskListRequest request);
    void deleteTaskListById(UUID id);

}
