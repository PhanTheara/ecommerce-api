package com.istad.theara.ecommerce_api.features.userProfile.dto;

public record PatchUserProfileRequest(
        String name,
        String lastname,
        String firstname,
        String gender,
        String biography,
        String facebookProfile,
        String telegramProfile,
        String pictureProfile,
        String phoneNumber
) {
}
