package com.jogwheel.todolistproject.rest;

import com.jogwheel.todolistproject.dto.request.CreateTaskListRequest;
import com.jogwheel.todolistproject.dto.request.UpdateTaskListRequest;
import com.jogwheel.todolistproject.dto.response.TaskListResponse;
import com.jogwheel.todolistproject.dto.response.TaskResponse;
import com.jogwheel.todolistproject.service.TaskListService;
import com.jogwheel.todolistproject.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasklists")
public class TaskListController {
    private final TaskListService taskListService;
    private final TaskService taskService;
    public TaskListController(TaskListService taskListService, TaskService taskService) {
        this.taskListService = taskListService;
        this.taskService = taskService;
    }
    @GetMapping
    public ResponseEntity<List<TaskListResponse>> getTaskLists() {
        return ResponseEntity.ok(taskListService.getTaskLists());
    }

    @GetMapping("/{taskListId}")
    public ResponseEntity<TaskListResponse> getTaskList(@PathVariable UUID taskListId) {
        return ResponseEntity.ok(taskListService.getTaskListById(taskListId));
    }

    @PostMapping
    public ResponseEntity<TaskListResponse> createTaskList(@Valid @RequestBody CreateTaskListRequest createTaskListRequest) {
        TaskListResponse taskListResponse = taskListService.createTaskList(createTaskListRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskListResponse);
    }

    @PutMapping("/{taskListId}")
    public ResponseEntity<TaskListResponse> updateTaskList(@PathVariable UUID taskListId,@Valid  @RequestBody UpdateTaskListRequest updateTaskListRequest) {
        TaskListResponse response = taskListService.updateTaskList(taskListId, updateTaskListRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{taskListId}")
    public ResponseEntity<Void> deleteTaskList(@PathVariable UUID taskListId) {
        taskListService.deleteTaskListById(taskListId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{taskListId}/tasks")
    public ResponseEntity<List<TaskResponse>> getTasksByTaskListId(@PathVariable UUID taskListId) {
        return ResponseEntity.ok(taskService.getTasksByTaskListId(taskListId));
    }
}
