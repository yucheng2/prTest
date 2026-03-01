package com.todolist.controller;

import com.todolist.dto.TodoItemDto;
import com.todolist.model.TodoItem;
import com.todolist.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/todos")
public class TodoItemController {
    @Autowired
    private TodoItemService todoItemService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TodoItemDto>> getAllTodos() {
        List<TodoItem> todos = todoItemService.findAll();
        List<TodoItemDto> todoDtos = todos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(todoDtos);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TodoItemDto> getTodoById(@PathVariable Long id) {
        TodoItem todo = todoItemService.findById(id);
        if (todo != null) {
            return ResponseEntity.ok(convertToDto(todo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TodoItemDto> createTodo(@RequestBody TodoItem todoItem) {
        TodoItem savedTodo = todoItemService.save(todoItem);
        return ResponseEntity.ok(convertToDto(savedTodo));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TodoItemDto> updateTodo(@PathVariable Long id, @RequestBody TodoItem todoItem) {
        TodoItem existingTodo = todoItemService.findById(id);
        if (existingTodo != null) {
            todoItem.setId(id);
            TodoItem updatedTodo = todoItemService.save(todoItem);
            return ResponseEntity.ok(convertToDto(updatedTodo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        TodoItem existingTodo = todoItemService.findById(id);
        if (existingTodo != null) {
            todoItemService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TodoItemDto>> getTodosByUserId(@PathVariable Long userId) {
        List<TodoItem> todos = todoItemService.findByUserId(userId);
        List<TodoItemDto> todoDtos = todos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(todoDtos);
    }

    @GetMapping("/user/{userId}/status/{status}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TodoItemDto>> getTodosByUserIdAndStatus(@PathVariable Long userId, @PathVariable TodoItem.Status status) {
        List<TodoItem> todos = todoItemService.findByUserIdAndStatus(userId, status);
        List<TodoItemDto> todoDtos = todos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(todoDtos);
    }

    private TodoItemDto convertToDto(TodoItem todoItem) {
        return new TodoItemDto(
                todoItem.getId(),
                todoItem.getTitle(),
                todoItem.getDescription(),
                todoItem.getStatus(),
                todoItem.getPriority(),
                todoItem.getDueDate(),
                todoItem.getCategoryId(),
                todoItem.getUserId(),
                todoItem.getCreatedAt(),
                todoItem.getUpdatedAt()
        );
    }
}