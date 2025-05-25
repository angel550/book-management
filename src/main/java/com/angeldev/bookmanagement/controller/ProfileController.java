package com.angeldev.bookmanagement.controller;

import com.angeldev.bookmanagement.dto.request.ProfileRequest;
import com.angeldev.bookmanagement.dto.response.BookResponse;
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

    @Operation(summary = "Get a profile by id", description = "Retrieve a specific profile by id")
    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> findProfile(@PathVariable Long id) {
        return new ResponseEntity<>(profileService.findProfile(id), HttpStatus.OK);
    }

    @Operation(summary = "Create a new profile", description = "Add a new profile to the database")
    @PostMapping
    public ResponseEntity<ProfileResponse> createProfile(@Valid @RequestBody ProfileRequest profileRequest) {
        return new ResponseEntity<>(profileService.createProfile(profileRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a profile", description = "Update the information of an existing profile")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProfileResponse> updateProfile(@PathVariable Long id, @Valid @RequestBody ProfileRequest profileRequest) {
        return new ResponseEntity<>(profileService.updateProfile(id, profileRequest), HttpStatus.OK);
    }

    @Operation(summary = "Delete a profile by id", description = "Remove a profile and their associated books from the database")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all books from a profile", description = "Get all books of a specific profile")
    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookResponse>> findAllBooks(@PathVariable Long id) {
        return new ResponseEntity<>(profileService.findAllBooks(id), HttpStatus.OK);
    }
}
