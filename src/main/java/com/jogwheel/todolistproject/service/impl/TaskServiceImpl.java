package com.jogwheel.todolistproject.service.impl;

import com.jogwheel.todolistproject.dto.request.CreateTaskRequest;
import com.jogwheel.todolistproject.dto.request.UpdateTaskRequest;
import com.jogwheel.todolistproject.dto.response.TaskResponse;
import com.jogwheel.todolistproject.entity.Task;
import com.jogwheel.todolistproject.entity.TaskList;
import com.jogwheel.todolistproject.exception.ResourceNotFoundException;
import com.jogwheel.todolistproject.mapper.TaskMapper;
import com.jogwheel.todolistproject.repository.TaskListRepository;
import com.jogwheel.todolistproject.repository.TaskRepository;
import com.jogwheel.todolistproject.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;
    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public TaskResponse createTask(CreateTaskRequest createTaskRequest) {
        TaskList taskList = taskListRepository.findById(createTaskRequest.getTaskListId())
                        .orElseThrow(() -> new ResourceNotFoundException("TaskList with id " + createTaskRequest.getTaskListId() + " does not exist"));
        Task task = TaskMapper.toEntity(createTaskRequest);
        task.setTaskList(taskList);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        Task saved = taskRepository.save(task);
        return TaskMapper.toResponse(saved);
    }

    @Override
    public TaskResponse updateTask(UUID id, UpdateTaskRequest updateTaskRequest) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));

        TaskMapper.updateEntity(task, updateTaskRequest);
        task.setUpdatedAt(LocalDateTime.now());

        Task updated = taskRepository.save(task);
        return TaskMapper.toResponse(updated);
    }

    @Override
    public void deleteTask(UUID id) {
        if(!taskRepository.existsById(id)) {throw new ResourceNotFoundException("Task with id " + id + " does not exist");}
        taskRepository.deleteById(id);
    }

    @Override
    public TaskResponse getTaskById(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        return TaskMapper.toResponse(task);
    }

    @Override
    public List<TaskResponse> getTasksByTaskListId(UUID taskListId) {
        return taskRepository.findByTaskList_Id(taskListId)
                .stream()
                .map(TaskMapper::toResponse)
                .collect(Collectors.toList());
    }
}
