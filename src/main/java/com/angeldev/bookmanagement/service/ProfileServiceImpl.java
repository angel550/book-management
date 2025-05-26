package com.angeldev.bookmanagement.service;

import com.angeldev.bookmanagement.dto.request.ProfileRequest;
import com.angeldev.bookmanagement.dto.response.BookResponse;
import com.angeldev.bookmanagement.dto.response.ProfileResponse;
import com.angeldev.bookmanagement.exception.DuplicateObjectException;
import com.angeldev.bookmanagement.exception.ObjectNotFoundException;
import com.angeldev.bookmanagement.mappers.BookMapper;
import com.angeldev.bookmanagement.mappers.ProfileMapper;
import com.angeldev.bookmanagement.persistence.entity.Book;
import com.angeldev.bookmanagement.persistence.entity.Profile;
import com.angeldev.bookmanagement.persistence.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<ProfileResponse> findAll() {
        return ProfileMapper.profileToProfileResponseList(profileRepository.findAll());
    }

    @Override
    public List<BookResponse> findAllBooks(Long id) {
        Profile profile = this.getProfile(id);

        List<Book> books = profile.getBooks();

        return BookMapper.bookToBookResponseList(books);
    }

    @Override
    public ProfileResponse findProfile(Long id) {
        Profile profile = this.getProfile(id);

        return ProfileMapper.profileToProfileResponse(profile);
    }

    @Transactional
    @Override
    public ProfileResponse createProfile(ProfileRequest profileRequest) {
        if (profileRepository.existsByName(profileRequest.name())) {
            throw new DuplicateObjectException(Profile.class.getSimpleName(), "Name");
        }

        Profile newProfile = ProfileMapper.profileRequestToProfile(profileRequest);

        return ProfileMapper.profileToProfileResponse(profileRepository.save(newProfile));
    }

    @Transactional
    @Override
    public ProfileResponse updateProfile(Long id, ProfileRequest profileRequest) {
        Profile oldProfile = this.getProfile(id);

        Profile newProfile = ProfileMapper.profileRequestToProfile(profileRequest);

        oldProfile.setName(newProfile.getName());
        oldProfile.setDescription(newProfile.getDescription());

        return ProfileMapper.profileToProfileResponse(profileRepository.save(oldProfile));
    }

    @Transactional
    @Override
    public void deleteProfile(Long id) {
        this.getProfile(id);

        profileRepository.deleteById(id);
    }

    private Profile getProfile(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        Profile.class.getSimpleName(),
                        id.toString()
                ));
    }
}
