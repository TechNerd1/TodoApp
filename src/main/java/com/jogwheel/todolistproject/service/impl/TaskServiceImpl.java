package com.jogwheel.todolistproject.service.impl;

import com.jogwheel.todolistproject.entity.Task;
import com.jogwheel.todolistproject.entity.TaskList;
import com.jogwheel.todolistproject.repository.TaskListRepository;
import com.jogwheel.todolistproject.repository.TaskRepository;
import com.jogwheel.todolistproject.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;
    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public Task createTask(Task task, UUID taskListId) {
        TaskList taskList = taskListRepository.findById(taskListId)
                        .orElseThrow(() -> new ResourceNotFoundException("TaskList with id " + taskListId + " does not exist"));
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setTaskList(taskList);
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        Task taskToUpdate = taskRepository.findById(task.getId()).orElseThrow(() -> new ResourceNotFoundException("Task with id " + task.getId() + " not found"));
        taskToUpdate.setUpdatedAt(LocalDateTime.now());
        if(task.isCompleted() && taskToUpdate.getCompletedAt() == null) {
            taskToUpdate.setCompletedAt(LocalDateTime.now());
        }
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(UUID id) {
        if(!taskRepository.existsById(id)) {throw new ResourceNotFoundException("Task with id " + id + " does not exist");}
        taskRepository.deleteById(id);
    }

    @Override
    public Task getTaskById(UUID id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
    }

    @Override
    public List<Task> getTasksByTaskListId(UUID taskListId) {
        return taskRepository.findByTaskList_Id(taskListId);
    }
}
