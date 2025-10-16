package com.jogwheel.todolistproject.repo;

import com.jogwheel.todolistproject.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    public List<Task> findByTaskList_Id(UUID taskListId);
}
