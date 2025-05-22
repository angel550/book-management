package com.angeldev.bookmanagement.service;

import com.angeldev.bookmanagement.dto.request.UserRequest;
import com.angeldev.bookmanagement.dto.response.UserResponse;
import com.angeldev.bookmanagement.mappers.UserMapper;
import com.angeldev.bookmanagement.persistence.entity.User;
import com.angeldev.bookmanagement.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> findAll() {
        List<User> users = new ArrayList<>();

        for (User u : userRepository.findAll()) {
            users.add(u);
        }

        return UserMapper.userToUserResponseList(users);
    }

    @Override
    public UserResponse findUserByUsername(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);

        if (user.isPresent()) {
            return UserMapper.userToUserResponse(user.get());
        }

        return null;
    }

    @Transactional
    @Override
    public UserResponse createUser(UserRequest userRequest) {
        if (!userRequest.password().equals(userRequest.passwordRepeated())) return null;

        User newUser = UserMapper.userRequestToUser(userRequest);

        return UserMapper.userToUserResponse(userRepository.save(newUser));
    }

    @Transactional
    @Override
    public UserResponse updateUserByUsername(String username, UserRequest userRequest) {
        if (!userRequest.password().equals(userRequest.passwordRepeated())) return null;

        Optional<User> user = userRepository.findUserByUsername(username);
        User oldUser = user.get();
        User newUser = UserMapper.userRequestToUser(userRequest);

        oldUser.setUsername(newUser.getUsername());
        oldUser.setName(newUser.getName());
        oldUser.setPassword(newUser.getPassword());

        return UserMapper.userToUserResponse(userRepository.save(oldUser));
    }

    @Transactional
    @Override
    public void deleteUserByUsername(String username) {
        if (userRepository.deleteUserByUsername(username) != 1) {
            System.out.println("error");
        }
    }
}
