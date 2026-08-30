package com.istad.theara.ecommerce_api.features.userProfile;

import com.istad.theara.ecommerce_api.features.userProfile.dto.PatchUserProfileRequest;
import com.istad.theara.ecommerce_api.features.userProfile.dto.UserProfileResponse;

public interface ServiceUserProfile {
    UserProfileResponse patchUserProfile(PatchUserProfileRequest request);
    UserProfileResponse getProfile();
}
