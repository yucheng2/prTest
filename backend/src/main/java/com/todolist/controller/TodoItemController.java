package com.todolist.controller;

import com.todolist.dto.TodoItemDto;
import com.todolist.model.TodoItem;
import com.todolist.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/todos")
public class TodoItemController {
    @Autowired
    TodoItemService todoItemService;

    @GetMapping
    public ResponseEntity<List<TodoItemDto>> getAllTodoItems(@RequestParam Long userId) {
        List<TodoItemDto> todos = todoItemService.getAllTodoItems(userId);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItemDto> getTodoItemById(@PathVariable Long id, @RequestParam Long userId) {
        TodoItemDto todo = todoItemService.getTodoItemById(id, userId);
        return ResponseEntity.ok(todo);
    }

    @PostMapping
    public ResponseEntity<TodoItemDto> createTodoItem(@RequestBody TodoItemDto todoItemDto, @RequestParam Long userId) {
        TodoItemDto createdTodo = todoItemService.createTodoItem(todoItemDto, userId);
        return ResponseEntity.ok(createdTodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoItemDto> updateTodoItem(@PathVariable Long id, @RequestBody TodoItemDto todoItemDto, @RequestParam Long userId) {
        TodoItemDto updatedTodo = todoItemService.updateTodoItem(id, todoItemDto, userId);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodoItem(@PathVariable Long id, @RequestParam Long userId) {
        todoItemService.deleteTodoItem(id, userId);
        return ResponseEntity.ok("Todo item deleted successfully");
    }

    @GetMapping("/status")
    public ResponseEntity<List<TodoItemDto>> getTodoItemsByStatus(@RequestParam Long userId, @RequestParam TodoItem.Status status) {
        List<TodoItemDto> todos = todoItemService.getTodoItemsByStatus(userId, status);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TodoItemDto>> searchTodoItems(@RequestParam Long userId, @RequestParam String keyword) {
        List<TodoItemDto> todos = todoItemService.searchTodoItems(userId, keyword);
        return ResponseEntity.ok(todos);
    }
}