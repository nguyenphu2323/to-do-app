package com.todo.todoapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.todo.todoapp.domain.Task;
import com.todo.todoapp.repository.TaskRepository;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return this.taskRepository.findAll();
    }

    public Task handleSaveTasks(Task task) {
        return this.taskRepository.save(task);
    }

    public Task deleteTaskById(long id) {
        return this.taskRepository.deleteById(id);
    }
}
