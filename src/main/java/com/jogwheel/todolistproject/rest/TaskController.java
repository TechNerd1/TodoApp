package com.jogwheel.todolistproject.rest;

import com.jogwheel.todolistproject.entity.Task;
import com.jogwheel.todolistproject.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
    public Task getTask(@PathVariable UUID taskId) {
        return taskService.getTaskById(taskId);
    }

    @PostMapping
    public Task createTask(@Valid @RequestBody Task task, @RequestParam UUID taskListId) {
        return taskService.createTask(task, taskListId);
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@Valid @PathVariable UUID taskId, @RequestBody Task task) {
        Task taskToUpdate = taskService.getTaskById(taskId);
        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setCompleted(task.isCompleted());
        return taskService.updateTask(taskToUpdate);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
    }



}
