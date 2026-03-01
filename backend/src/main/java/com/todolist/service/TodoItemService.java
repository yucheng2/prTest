package com.todolist.service;

import com.todolist.model.TodoItem;
import com.todolist.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {
    @Autowired
    private TodoItemRepository todoItemRepository;

    public List<TodoItem> findAll() {
        return todoItemRepository.findAll();
    }

    public TodoItem findById(Long id) {
        Optional<TodoItem> todoItem = todoItemRepository.findById(id);
        return todoItem.orElse(null);
    }

    public TodoItem save(TodoItem todoItem) {
        return todoItemRepository.save(todoItem);
    }

    public void deleteById(Long id) {
        todoItemRepository.deleteById(id);
    }

    public List<TodoItem> findByUserId(Long userId) {
        return todoItemRepository.findByUserId(userId);
    }

    public List<TodoItem> findByUserIdAndStatus(Long userId, TodoItem.Status status) {
        return todoItemRepository.findByUserIdAndStatus(userId, status);
    }
}