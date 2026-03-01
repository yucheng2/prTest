package com.todolist.service;

import com.todolist.model.User;
import com.todolist.dto.UserDto;
import com.todolist.dto.AuthRequest;
import com.todolist.dto.AuthResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> registerUser(AuthRequest authRequest);
    ResponseEntity<?> loginUser(AuthRequest authRequest);
    UserDto authenticateAndGetUser(String token);
    User findUserById(Long id);
    boolean doesUserExist(String username);
}