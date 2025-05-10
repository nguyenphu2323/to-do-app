package com.todo.todoapp.service.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.todo.todoapp.domain.Task;
import com.todo.todoapp.domain.Task_;

public class TaskSpecs {
    public static Specification<Task> matchListStatus(List<String> status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(Task_.STATUS)).value(status);
    }

    // public static Specification<Task> matchcreatedDate(List<String> createdDate)
    // {
    // return (root, query, criteriaBuilder) ->
    // criteriaBuilder.in(root.get(Task_.CREATED_DATE));
    // }

}
