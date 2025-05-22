package com.angeldev.bookmanagement.mappers;

import com.angeldev.bookmanagement.dto.request.UserRequest;
import com.angeldev.bookmanagement.dto.response.UserResponse;
import com.angeldev.bookmanagement.persistence.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static UserResponse userToUserResponse(User user) {
        if (user == null) return null;

        return new UserResponse(
                user.getUsername(),
                user.getName()
        );
    }

    public static User userRequestToUser(UserRequest userRequest) {
        if (userRequest == null) return null;

        return new User(
                userRequest.username(),
                userRequest.name(),
                userRequest.password(),
                null
        );
    }

    public static List<UserResponse> userToUserResponseList(List<User> users) {
        List<UserResponse> userResponseList = new ArrayList<>();

        for (User u: users) {
            userResponseList.add(userToUserResponse(u));
        }

        return userResponseList;
    }
}
