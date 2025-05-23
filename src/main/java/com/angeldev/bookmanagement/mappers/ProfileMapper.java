package com.angeldev.bookmanagement.mappers;

import com.angeldev.bookmanagement.dto.request.ProfileRequest;
import com.angeldev.bookmanagement.dto.response.ProfileResponse;
import com.angeldev.bookmanagement.persistence.entity.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileMapper {

    public static ProfileResponse profileToProfileResponse(Profile profile) {
        return new ProfileResponse(
                profile.getName(),
                profile.getDescription()
        );
    }

    public static Profile profileRequestToProfile(ProfileRequest profileRequest) {
        return new Profile(
                profileRequest.name(),
                profileRequest.description(),
                null
        );
    }

    public static List<ProfileResponse> profileToProfileResponseList(List<Profile> profiles) {
        List<ProfileResponse> profileResponseList = new ArrayList<>();

        for (Profile p: profiles) {
            profileResponseList.add(profileToProfileResponse(p));
        }

        return profileResponseList;
    }
}
