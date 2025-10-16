package com.jogwheel.todolistproject.mapper;

import com.jogwheel.todolistproject.dto.request.CreateTaskRequest;
import com.jogwheel.todolistproject.dto.request.UpdateTaskRequest;
import com.jogwheel.todolistproject.dto.response.TaskResponse;
import com.jogwheel.todolistproject.entity.Task;

import java.time.LocalDateTime;

public class TaskMapper {
    public static Task toEntity(CreateTaskRequest request){
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        return task;
    }

    public static TaskResponse toResponse(Task task){
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted(),
                task.getCompletedAt(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

    public static void updateEntity(Task task, UpdateTaskRequest request){
        if(request.getTitle() != null){
            task.setTitle(request.getTitle());
        }
        if(request.getDescription() != null){
            task.setDescription(request.getDescription());
        }
        if(request.isCompleted() && task.getCompletedAt() == null){
            task.setCompletedAt(LocalDateTime.now());
        }
    }
}
