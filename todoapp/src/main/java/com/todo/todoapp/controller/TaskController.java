package com.todo.todoapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.todo.todoapp.domain.Task;
import com.todo.todoapp.service.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/task")
    public String getTaskPage(Model model) {
        List<Task> tasks = this.taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "/Task/show";
    }

    @GetMapping("/task/create")
    public String getCreateTaskPage(Model model) {
        model.addAttribute("newTask", new Task());
        return "/Task/create";
    }

    @PostMapping("/task/create")
    public String createTaskPage(Model model, @ModelAttribute("newTask") Task task) {
        // TODO: process POST request
        task.setStatus("TODO");
        this.taskService.handleSaveTasks(task);
        return "redirect:/task";
    }

    @GetMapping("/task/delete/{id}")
    public String getDeleteTaskPage(Model model, @PathVariable long id) {
        model.addAttribute("newTask", new Task());
        model.addAttribute("id", id);
        return "/Task/delete";
    }

}
