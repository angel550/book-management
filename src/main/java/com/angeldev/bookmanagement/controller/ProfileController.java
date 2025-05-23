package com.angeldev.bookmanagement.controller;

import com.angeldev.bookmanagement.dto.request.ProfileRequest;
import com.angeldev.bookmanagement.dto.response.ProfileResponse;
import com.angeldev.bookmanagement.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/profiles")
@RestController
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<ProfileResponse>> findAll() {
        return new ResponseEntity<>(profileService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProfileResponse> findProfile(@PathVariable String name) {
        return new ResponseEntity<>(profileService.findProfile(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfileResponse> createProfile(@Valid @RequestBody ProfileRequest profileRequest) {
        return new ResponseEntity<>(profileService.createProfile(profileRequest), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{name}")
    public ResponseEntity<ProfileResponse> updateProfile(@PathVariable String name, @Valid @RequestBody ProfileRequest profileRequest) {
        return new ResponseEntity<>(profileService.updateProfile(name, profileRequest), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity<Void> deleteProfile(@PathVariable String name) {
        profileService.deleteProfile(name);
        return ResponseEntity.noContent().build();
    }
}
