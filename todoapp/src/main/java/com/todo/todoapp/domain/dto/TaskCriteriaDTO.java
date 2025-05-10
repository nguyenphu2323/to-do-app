package com.todo.todoapp.domain.dto;

import java.util.List;
import java.util.Optional;

public class TaskCriteriaDTO {
    private Optional<List<String>> status;
    private Optional<List<String>> priority;
    private Optional<String> sort;
    private Optional<String> page;
    private Optional<List<String>> createdDate;

    public Optional<List<String>> getStatus() {
        return status;
    }

    public void setStatus(Optional<List<String>> status) {
        this.status = status;
    }

    public Optional<String> getSort() {
        return sort;
    }

    public void setSort(Optional<String> sort) {
        this.sort = sort;
    }

    public Optional<String> getPage() {
        return page;
    }

    public void setPage(Optional<String> page) {
        this.page = page;
    }

    public Optional<List<String>> getPriority() {
        return priority;
    }

    public void setPriority(Optional<List<String>> priority) {
        this.priority = priority;
    }

    public Optional<List<String>> getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Optional<List<String>> createdDate) {
        this.createdDate = createdDate;
    }

}
