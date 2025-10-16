package com.jogwheel.todolistproject.mapper;

import com.jogwheel.todolistproject.dto.request.CreateTaskListRequest;
import com.jogwheel.todolistproject.dto.request.UpdateTaskListRequest;
import com.jogwheel.todolistproject.dto.response.TaskListResponse;
import com.jogwheel.todolistproject.entity.TaskList;

public class TaskListMapper {
    public static TaskList toEntity(CreateTaskListRequest request) {
        TaskList taskList = new TaskList();
        taskList.setTitle(request.getTitle());
        taskList.setDescription(request.getDescription());
        return taskList;
    }

    public static TaskListResponse toResponse(TaskList taskList) {
        return new TaskListResponse(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                taskList.getCompleted(),
                taskList.getCompletedAt(),
                taskList.getCreatedAt(),
                taskList.getUpdatedAt()
        );
    }

    public static void updateEntity(TaskList taskList, UpdateTaskListRequest request) {
        if(request.getTitle() != null){
            taskList.setTitle(request.getTitle());
        }
        if(request.getDescription() != null){
            taskList.setDescription(request.getDescription());
        }
    }
}
