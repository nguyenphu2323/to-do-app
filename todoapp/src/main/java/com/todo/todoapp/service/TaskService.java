package com.todo.todoapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.todo.todoapp.domain.Task;
import com.todo.todoapp.repository.TaskRepository;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Page<Task> getAllTasks(Pageable pageable) {
        return this.taskRepository.findAll(pageable);
    }

    public Task handleSaveTasks(Task task) {
        return this.taskRepository.save(task);
    }

    public Task deleteTaskById(long id) {
        return this.taskRepository.deleteById(id);
    }

    public Task getTaskById(long id) {
        return this.taskRepository.findById(id);
    }
}
