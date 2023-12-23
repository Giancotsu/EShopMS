package com.eshop.user.controllers;

import com.eshop.user.dto.LoginRequest;
import com.eshop.user.entities.UserEntity;
import com.eshop.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<UserEntity> saveNewUser(@RequestBody UserEntity user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@RequestBody LoginRequest user) {
        return new ResponseEntity<>(userService.login(user), HttpStatus.CREATED);
    }
}
