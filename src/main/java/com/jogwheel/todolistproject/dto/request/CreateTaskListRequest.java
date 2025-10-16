package com.jogwheel.todolistproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateTaskListRequest {
    @NotBlank(message = "Title is required")
    @Size(max = 255)
    private String title;

    @Size(max = 1000)
    private String description;

    public CreateTaskListRequest(){}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
}
