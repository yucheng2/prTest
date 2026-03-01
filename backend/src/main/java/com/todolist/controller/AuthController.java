package com.todolist.controller;

import com.todolist.dto.AuthRequest;
import com.todolist.dto.AuthResponse;
import com.todolist.dto.UserDto;
import com.todolist.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AuthRequest authRequest) {
        return authService.registerUser(authRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest authRequest) {
        return authService.loginUser(authRequest);
    }
}