package com.angeldev.bookmanagement.controller;

import com.angeldev.bookmanagement.dto.request.UserRequest;
import com.angeldev.bookmanagement.dto.response.UserResponse;
import com.angeldev.bookmanagement.service.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{username}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String username, @Valid @RequestBody UserRequest userRequest) {
        System.out.println(username);
        System.out.println(userRequest.username());
        return new ResponseEntity<>(userService.updateUserByUsername(username, userRequest), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return ResponseEntity.noContent().build();
    }
}
