package com.bob.task.service.service;

import com.bob.task.service.model.Task;
import com.bob.task.service.model.TaskStatus;

import java.util.List;

public interface TaskService {
    Task createTask(Task task, String requesterRole) throws Exception;
    Task getTaskById(Long id) throws Exception;
    List<Task> getAllTask(TaskStatus status);
    Task updatedTask(Long id, Task updatedTask, Long userId) throws Exception;
    void deleteTask(Long id) throws Exception;
    Task assignedToUser(Long userId, Long taskId) throws Exception;
    List<Task> assignedUsersTask(Long userId, TaskStatus status);
    Task completeTask(Long taskId) throws Exception;
}
