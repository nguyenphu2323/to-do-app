package com.todo.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.todoapp.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task deleteById(long id);
}
