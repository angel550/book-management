package com.angeldev.bookmanagement.service;

import com.angeldev.bookmanagement.dto.request.UserRequest;
import com.angeldev.bookmanagement.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> findAll();
    UserResponse findUser(String username);
    UserResponse updateUser(String username, UserRequest userRequest);
    UserResponse createUser(UserRequest userRequest);
    void deleteUser(String username);
}
