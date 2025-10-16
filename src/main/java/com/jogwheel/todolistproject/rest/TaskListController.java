package com.jogwheel.todolistproject.rest;

import com.jogwheel.todolistproject.entity.Task;
import com.jogwheel.todolistproject.entity.TaskList;
import com.jogwheel.todolistproject.service.TaskListService;
import com.jogwheel.todolistproject.service.TaskService;
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
    public List<TaskList> getTaskLists() {
        return taskListService.getTaskLists();
    }

    @GetMapping("/{taskListId}")
    public TaskList getTaskList(@PathVariable UUID taskListId) {
        return taskListService.getTaskListById(taskListId);
    }

    @PostMapping
    public TaskList createTaskList(@RequestBody TaskList taskList) {
        return taskListService.createTaskList(taskList);
    }

    @PutMapping("/{taskListId}")
    public TaskList updateTaskList(@PathVariable UUID taskListId, @RequestBody TaskList taskList) {
        TaskList tempTaskList = taskListService.getTaskListById(taskListId);
        tempTaskList.setDescription(taskList.getDescription());
        tempTaskList.setTitle(taskList.getTitle());
        tempTaskList.setCompleted(taskList.getCompleted());
        return taskListService.updateTaskList(tempTaskList);
    }

    @DeleteMapping("/{taskListId}")
    public String deleteTaskList(@RequestParam UUID taskListId) {
        taskListService.deleteTaskListById(taskListId);
        return "TaskList with id " + taskListId + " has been deleted";
    }

    @GetMapping("/{taskListId}/tasks")
    public List<Task> getTasksByTaskListId(@PathVariable UUID taskListId) {
        return taskService.getTasksByTaskListId(taskListId);
    }
}
