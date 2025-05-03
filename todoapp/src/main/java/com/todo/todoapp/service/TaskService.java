package com.todo.todoapp.service;

import org.springframework.stereotype.Service;

import com.todo.todoapp.repository.TaskRepository;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

}
