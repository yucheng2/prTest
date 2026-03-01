package com.todolist.repository;

import com.todolist.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    List<TodoItem> findByUserId(Long userId);
    List<TodoItem> findByUserIdAndStatus(Long userId, TodoItem.Status status);
    List<TodoItem> findByUserIdAndCategoryId(Long userId, Long categoryId);
    List<TodoItem> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<TodoItem> findByUserIdOrderByDueDateAsc(Long userId);
    
    @Query("SELECT t FROM TodoItem t WHERE t.userId = :userId AND LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<TodoItem> findByUserIdAndTitleContaining(@Param("userId") Long userId, @Param("keyword") String keyword);
}