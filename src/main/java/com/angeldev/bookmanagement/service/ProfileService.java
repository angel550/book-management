package com.angeldev.bookmanagement.service;

import com.angeldev.bookmanagement.dto.request.ProfileRequest;
import com.angeldev.bookmanagement.dto.response.ProfileResponse;

import java.util.List;

public interface ProfileService {

    List<ProfileResponse> findAll();
    ProfileResponse findProfile(String name);
    ProfileResponse updateProfile(String name, ProfileRequest profileRequest);
    ProfileResponse createProfile(ProfileRequest profileRequest);
    void deleteProfile(String name);
}
