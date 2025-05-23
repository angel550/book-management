package com.angeldev.bookmanagement.service;

import com.angeldev.bookmanagement.dto.request.ProfileRequest;
import com.angeldev.bookmanagement.dto.response.ProfileResponse;
import com.angeldev.bookmanagement.mappers.ProfileMapper;
import com.angeldev.bookmanagement.persistence.entity.Profile;
import com.angeldev.bookmanagement.persistence.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<ProfileResponse> findAll() {
        List<Profile> profiles = new ArrayList<>();

        for (Profile p : profileRepository.findAll()) {
            profiles.add(p);
        }

        return ProfileMapper.profileToProfileResponseList(profiles);
    }

    @Override
    public ProfileResponse findProfile(String name) {
        Optional<Profile> profile = profileRepository.findProfileByName(name);

        if (profile.isPresent()) {
            return ProfileMapper.profileToProfileResponse(profile.get());
        }

        return null;
    }

    @Transactional
    @Override
    public ProfileResponse createProfile(ProfileRequest profileRequest) {
        Profile newProfile = ProfileMapper.profileRequestToProfile(profileRequest);

        return ProfileMapper.profileToProfileResponse(profileRepository.save(newProfile));
    }

    @Transactional
    @Override
    public ProfileResponse updateProfile(String name, ProfileRequest profileRequest) {
        Optional<Profile> profile = profileRepository.findProfileByName(name);
        Profile oldProfile = profile.get();

        Profile newProfile = ProfileMapper.profileRequestToProfile(profileRequest);

        oldProfile.setName(newProfile.getName());
        oldProfile.setDescription(newProfile.getDescription());

        return ProfileMapper.profileToProfileResponse(profileRepository.save(oldProfile));
    }

    @Transactional
    @Override
    public void deleteProfile(String name) {
        if (profileRepository.deleteProfileByName(name) != 1) {
            System.out.println("error");
        }
    }
}
