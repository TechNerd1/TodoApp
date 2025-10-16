package com.jogwheel.todolistproject.service.impl;

import com.jogwheel.todolistproject.dto.request.CreateTaskListRequest;
import com.jogwheel.todolistproject.dto.request.UpdateTaskListRequest;
import com.jogwheel.todolistproject.dto.request.UpdateTaskRequest;
import com.jogwheel.todolistproject.dto.response.TaskListResponse;
import com.jogwheel.todolistproject.entity.TaskList;
import com.jogwheel.todolistproject.mapper.TaskListMapper;
import com.jogwheel.todolistproject.repository.TaskListRepository;
import com.jogwheel.todolistproject.service.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;
    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public TaskListResponse getTaskListById(UUID id) {
        TaskList taskList = taskListRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TaskList with id " + id + " not found"));
        return TaskListMapper.toResponse(taskList);
    }

    @Override
    public List<TaskListResponse> getTaskLists() {
        return taskListRepository.findAll()
                .stream()
                .map(TaskListMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TaskListResponse createTaskList(CreateTaskListRequest createTaskListRequest) {
        TaskList taskList = TaskListMapper.toEntity(createTaskListRequest);
        taskList.setCreatedAt(LocalDateTime.now());
        taskList.setUpdatedAt(LocalDateTime.now());
        TaskList saved = taskListRepository.save(taskList);
        return TaskListMapper.toResponse(saved);
    }

    @Override
    public TaskListResponse updateTaskList(UUID id, UpdateTaskListRequest request) {
        TaskList taskList = taskListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TaskList with id " + id + " not found"));
        TaskListMapper.updateEntity(taskList, request);
        taskList.setUpdatedAt(LocalDateTime.now());

        TaskList updated = taskListRepository.save(taskList);

        return TaskListMapper.toResponse(updated);
    }

    @Override
    public void deleteTaskListById(UUID id) {
        if(!taskListRepository.existsById(id)) {throw new ResourceNotFoundException("TaskList with id " + id + " not found");}
        taskListRepository.deleteById(id);
    }
}
