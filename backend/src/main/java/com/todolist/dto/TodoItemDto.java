package com.todolist.dto;

import com.todolist.model.TodoItem;
import java.time.LocalDateTime;

public class TodoItemDto {
    private Long id;
    private String title;
    private String description;
    private TodoItem.Status status;
    private TodoItem.Priority priority;
    private LocalDateTime dueDate;
    private Long categoryId;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public TodoItemDto() {}

    public TodoItemDto(Long id, String title, String description, TodoItem.Status status, 
                       TodoItem.Priority priority, LocalDateTime dueDate, Long categoryId, Long userId,
                       LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
        this.categoryId = categoryId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TodoItem.Status getStatus() {
        return status;
    }

    public void setStatus(TodoItem.Status status) {
        this.status = status;
    }

    public TodoItem.Priority getPriority() {
        return priority;
    }

    public void setPriority(TodoItem.Priority priority) {
        this.priority = priority;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}