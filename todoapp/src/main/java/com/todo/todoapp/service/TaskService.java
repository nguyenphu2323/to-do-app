package com.todo.todoapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.todo.todoapp.domain.Task;
import com.todo.todoapp.domain.dto.TaskCriteriaDTO;
import com.todo.todoapp.repository.TaskRepository;
import com.todo.todoapp.service.specification.TaskSpecs;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Page<Task> getAllTasks(Pageable pageable, TaskCriteriaDTO taskCriteriaDTO) {
        if (taskCriteriaDTO.getStatus() == null) {
            return this.taskRepository.findAll(pageable);
        }
        Specification<Task> combinedSpec = Specification.where(null);
        if (taskCriteriaDTO.getStatus() != null && taskCriteriaDTO.getStatus().isPresent()) {
            Specification<Task> currentSpecs = TaskSpecs.matchListStatus(taskCriteriaDTO.getStatus().get());
            combinedSpec = combinedSpec.and(currentSpecs);
        }
        // if (taskCriteriaDTO.getCreatedDate() != null &&
        // taskCriteriaDTO.getCreatedDate().isPresent()) {
        // Specification<Task> currentSpecs =
        // TaskSpecs.matchcreatedDate(taskCriteriaDTO.getCreatedDate().get());
        // combinedSpec = combinedSpec.and(currentSpecs);
        // }

        return this.taskRepository.findAll(combinedSpec, pageable);
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
