package com.todolist.service;

import com.todolist.model.TodoItem;
import com.todolist.dto.TodoItemDto;
import java.util.List;

public interface TodoItemService {
    List<TodoItemDto> getAllTodoItems(Long userId);
    TodoItemDto getTodoItemById(Long id, Long userId);
    TodoItemDto createTodoItem(TodoItemDto todoItemDto, Long userId);
    TodoItemDto updateTodoItem(Long id, TodoItemDto todoItemDto, Long userId);
    void deleteTodoItem(Long id, Long userId);
    List<TodoItemDto> getTodoItemsByStatus(Long userId, TodoItem.Status status);
    List<TodoItemDto> getTodoItemsByCategoryId(Long userId, Long categoryId);
    List<TodoItemDto> searchTodoItems(Long userId, String keyword);
}