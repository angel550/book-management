package com.angeldev.bookmanagement.service;

import com.angeldev.bookmanagement.dto.request.ProfileRequest;
import com.angeldev.bookmanagement.dto.response.BookResponse;
import com.angeldev.bookmanagement.dto.response.ProfileResponse;

import java.util.List;

public interface ProfileService {

    List<ProfileResponse> findAll();
    List<BookResponse> findAllBooks(Long id);
    ProfileResponse findProfile(Long id);
    ProfileResponse updateProfile(Long id, ProfileRequest profileRequest);
    ProfileResponse createProfile(ProfileRequest profileRequest);
    void deleteProfile(Long id);
}
