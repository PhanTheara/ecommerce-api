package com.istad.theara.ecommerce_api.features.userProfile;

import com.istad.theara.ecommerce_api.features.userProfile.dto.PatchUserProfileRequest;
import com.istad.theara.ecommerce_api.features.userProfile.dto.UserProfileResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-profiles")
@RequiredArgsConstructor
public class UserProfileController {

    private final ServiceUserProfile userProfileService;

    @PatchMapping
    public UserProfileResponse patchUserProfile(
            @Valid
            @RequestBody
            PatchUserProfileRequest patchUserProfileRequest
    ) {
        return userProfileService.patchUserProfile(patchUserProfileRequest);
    }
    @GetMapping
    public UserProfileResponse getProfile(){
        return userProfileService.getProfile();
    }
}
