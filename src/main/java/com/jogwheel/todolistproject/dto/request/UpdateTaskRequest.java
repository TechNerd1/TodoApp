package com.jogwheel.todolistproject.dto.request;

import jakarta.validation.constraints.Size;

public class UpdateTaskRequest {
    @Size(max = 255)
    private String title;
    @Size(max = 1000)
    private String description;

    private Boolean completed;
    public UpdateTaskRequest() {
    }

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

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }


}
