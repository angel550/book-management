package com.angeldev.bookmanagement.controller;

import com.angeldev.bookmanagement.dto.request.ProfileRequest;
import com.angeldev.bookmanagement.dto.response.ProfileResponse;
import com.angeldev.bookmanagement.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Profile REST API Endpoints", description = "Operations for managing profiles")
@RequestMapping("/profiles")
@RestController
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Operation(summary = "Get all profiles", description = "Fetch all profiles registered")
    @GetMapping
    public ResponseEntity<List<ProfileResponse>> findAll() {
        return new ResponseEntity<>(profileService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get a profile by name", description = "Retrieve a specific profile by name")
    @GetMapping("/{name}")
    public ResponseEntity<ProfileResponse> findProfile(@PathVariable String name) {
        return new ResponseEntity<>(profileService.findProfile(name), HttpStatus.OK);
    }

    @Operation(summary = "Create a new profile", description = "Add a new profile to the database")
    @PostMapping
    public ResponseEntity<ProfileResponse> createProfile(@Valid @RequestBody ProfileRequest profileRequest) {
        return new ResponseEntity<>(profileService.createProfile(profileRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a profile", description = "Update the information of an existing profile")
    @PutMapping(value = "/{name}")
    public ResponseEntity<ProfileResponse> updateProfile(@PathVariable String name, @Valid @RequestBody ProfileRequest profileRequest) {
        return new ResponseEntity<>(profileService.updateProfile(name, profileRequest), HttpStatus.OK);
    }

    @Operation(summary = "Delete a profile", description = "Remove a profile and their associated books from the database")
    @DeleteMapping(value = "/{name}")
    public ResponseEntity<Void> deleteProfile(@PathVariable String name) {
        profileService.deleteProfile(name);
        return ResponseEntity.noContent().build();
    }
}
