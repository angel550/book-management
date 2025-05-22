package com.angeldev.bookmanagement.service;

import com.angeldev.bookmanagement.dto.request.UserRequest;
import com.angeldev.bookmanagement.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> findAll();
    UserResponse findUserByUsername(String username);
    UserResponse updateUserByUsername(String username, UserRequest userRequest);
    UserResponse createUser(UserRequest userRequest);
    void deleteUserByUsername(String username);
}
