package com.jogwheel.todolistproject.rest;

import com.jogwheel.todolistproject.dto.request.CreateTaskRequest;
import com.jogwheel.todolistproject.dto.request.UpdateTaskRequest;
import com.jogwheel.todolistproject.dto.response.TaskResponse;
import com.jogwheel.todolistproject.entity.Task;
import com.jogwheel.todolistproject.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable UUID taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest createTaskRequest) {
        TaskResponse taskResponse = taskService.createTask(createTaskRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponse);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable UUID taskId, @Valid @RequestBody UpdateTaskRequest updateTaskRequest) {
        TaskResponse taskResponse = taskService.updateTask(taskId, updateTaskRequest);
        return ResponseEntity.ok(taskResponse);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        if(taskService.getTaskById(taskId) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }



}
