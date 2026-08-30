package com.istad.theara.ecommerce_api.features.userProfile;

import com.istad.theara.ecommerce_api.features.userProfile.dto.PatchUserProfileRequest;
import com.istad.theara.ecommerce_api.features.userProfile.dto.UserProfileResponse;
import com.istad.theara.ecommerce_api.features.userProfile.mapper.UserProfileMapper;
import com.istad.theara.ecommerce_api.features.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ServiceUserProfileImpl implements ServiceUserProfile{
    private  final Keycloak keycloak;
    private  final  UserProfileRepository userProfileRepository;
    private  final UserProfileMapper userProfileMapper;
    @Value("${keycloak.realm}")
    private String realm;

    @Override
    public UserProfileResponse patchUserProfile(PatchUserProfileRequest patchUserProfileRequest) {

        String userId = AuthUtils.extractUserId();
        assert userId != null;
        UserProfile userProfile = userProfileRepository.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User profile has not been found"));

        userProfileMapper.toEntity(patchUserProfileRequest, userProfile);
        userProfileRepository.save(userProfile);

        // Step 2: Update userProfile in keycloak
        UsersResource usersResource = keycloak.realm(realm).users();
        UserResource userResource = usersResource.get(userId);
        UserRepresentation userRepresentation = userResource.toRepresentation();

        if (patchUserProfileRequest.firstname() != null)
            userRepresentation.setFirstName(patchUserProfileRequest.firstname());
        if (patchUserProfileRequest.lastname() != null)
            userRepresentation.setLastName(patchUserProfileRequest.lastname());

        return userProfileMapper.toResponse(userProfile, patchUserProfileRequest.lastname(),patchUserProfileRequest.firstname(),userRepresentation.getAttributes().get("PhoneNumber").getFirst());
    }

    @Override
    public UserProfileResponse getProfile() {
        String userId = AuthUtils.extractUserId();
        assert userId != null;
        UserProfile userProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User profile has not been found"));
        // Step 2: Update userProfile in keycloak
        UsersResource usersResource = keycloak.realm(realm).users();
        UserResource userResource = usersResource.get(userId);
        UserRepresentation userRepresentation = userResource.toRepresentation();


        return userProfileMapper.toResponse(userProfile, userRepresentation.getLastName(),userRepresentation.getFirstName(),userRepresentation.getAttributes().get("PhoneNumber").getFirst());

    }
}
