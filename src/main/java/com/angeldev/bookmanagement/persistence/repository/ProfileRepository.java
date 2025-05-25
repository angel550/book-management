package com.angeldev.bookmanagement.persistence.repository;

import com.angeldev.bookmanagement.persistence.entity.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {
    Optional<Profile> findProfileById(Long id);
    int deleteProfileById(Long id);
    boolean existsByName(String name);
}
