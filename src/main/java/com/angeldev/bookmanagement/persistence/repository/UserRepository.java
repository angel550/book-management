package com.angeldev.bookmanagement.persistence.repository;

import com.angeldev.bookmanagement.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    int deleteUserByUsername(String username);
}
