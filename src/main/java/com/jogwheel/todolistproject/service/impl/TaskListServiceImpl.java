package com.jogwheel.todolistproject.service.impl;

import com.jogwheel.todolistproject.entity.TaskList;
import com.jogwheel.todolistproject.repository.TaskListRepository;
import com.jogwheel.todolistproject.service.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;
    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public TaskList getTaskListById(UUID id) {
        return taskListRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TaskList with id " + id + " not found"));
    }

    @Override
    public List<TaskList> getTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        taskList.setCreatedAt(LocalDateTime.now());
        taskList.setUpdatedAt(LocalDateTime.now());
        return taskListRepository.save(taskList);
    }

    @Override
    public TaskList updateTaskList(TaskList taskList) {
        taskListRepository.findById(taskList.getId())
                .orElseThrow(() -> new ResourceNotFoundException("TaskList with id " + taskList.getId() + " not found"));
        taskList.setUpdatedAt(LocalDateTime.now());
        return taskListRepository.save(taskList);
    }

    @Override
    public void deleteTaskListById(UUID id) {
        if(!taskListRepository.existsById(id)) {throw new ResourceNotFoundException("TaskList with id " + id + " not found");}
        taskListRepository.deleteById(id);
    }
}
