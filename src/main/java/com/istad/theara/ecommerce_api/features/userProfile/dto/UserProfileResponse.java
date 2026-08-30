package com.istad.theara.ecommerce_api.features.userProfile.dto;

import lombok.Builder;

@Builder
public record UserProfileResponse (
        String id,
        String lastname,
        String gender,
        String biography,
        String facebookProfile,
        String telegramProfile,
        String pictureProfile,
        String phoneNumber
) { }
