package com.jogwheel.todolistproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class CreateTaskRequest {
    @NotBlank(message = "Title is required")
    @Size(max = 255)
    private String title;

    @Size(max = 1000)
    private String description;

    @NotNull(message = "TaskList ID is required")
    private UUID taskListId;

    public CreateTaskRequest() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(UUID taskListId) {
        this.taskListId = taskListId;
    }
}
