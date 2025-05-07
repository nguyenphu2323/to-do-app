package com.todo.todoapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.todo.todoapp.domain.Task;
import com.todo.todoapp.service.TaskService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/task")
    public String getTaskPage(Model model, @RequestParam("page") Optional<String> pageOptional) {
        int page = 1;
        try {
            if (pageOptional.isPresent()) {
                page = Integer.parseInt(pageOptional.get());
            } else {

            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        Pageable pageable = PageRequest.of(page - 1, 4);
        Page<Task> pageTask = this.taskService.getAllTasks(pageable);
        List<Task> tasks = pageTask.getContent();
        model.addAttribute("tasks", tasks);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageTask.getTotalPages());
        return "Task/show";
    }

    @GetMapping("task/create")
    public String getCreateTaskPage(Model model) {
        model.addAttribute("newTask", new Task());
        return "task/create";
    }

    @PostMapping("/task/create")
    public String createTaskPage(Model model, @Valid @ModelAttribute("newTask") Task task,
            BindingResult bindingResult) {
        // TODO: process POST request
        if (bindingResult.hasErrors()) {
            return "task/create";
        }
        task.setStatus("TODO");
        this.taskService.handleSaveTasks(task);
        return "redirect:/task";
    }

    @GetMapping("/task/delete/{id}")
    public String getDeleteTaskPage(Model model, @PathVariable long id) {
        model.addAttribute("newTask", new Task());
        model.addAttribute("id", id);
        return "Task/delete";
    }

    @PostMapping("/task/delete")
    public String deleteTaskPage(Model model, @ModelAttribute("newTask") Task task) {
        this.taskService.deleteTaskById(task.getId());
        return "redirect:/task";
    }

    @GetMapping("/task/update/{id}")
    public String getUpdateTaskPage(Model model, @PathVariable long id) {
        Task currentTask = this.taskService.getTaskById(id);
        model.addAttribute("newTask", currentTask);
        return "Task/update";
    }

    @PostMapping("/task/update")
    public String updateTaskPage(Model model, @Valid @ModelAttribute("newTask") Task task,
            BindingResult bindingResult) {
        // TODO: process POST request
        if (bindingResult.hasErrors()) {
            return "task/update";
        }
        Task currentTask = this.taskService.getTaskById(task.getId());
        if (currentTask != null) {
            currentTask.setTitle(task.getTitle());
            currentTask.setDescription(task.getDescription());
            currentTask.setStatus(task.getStatus());
            currentTask.setPriority(task.getPriority());
            this.taskService.handleSaveTasks(currentTask);
        }
        return "redirect:/task";
    }

}
