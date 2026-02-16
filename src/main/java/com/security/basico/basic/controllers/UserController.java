package com.security.basico.basic.controllers;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.security.basico.basic.dto.UserRequestDto;
import com.security.basico.basic.entities.User;
//import com.security.basico.basic.service.UserService;
import com.security.basico.basic.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(userService.create(dto));
    }
}
