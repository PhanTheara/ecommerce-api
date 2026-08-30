package com.istad.theara.ecommerce_api.features.userProfile.mapper;
import com.istad.theara.ecommerce_api.features.userProfile.UserProfile;
import com.istad.theara.ecommerce_api.features.userProfile.dto.PatchUserProfileRequest;
import com.istad.theara.ecommerce_api.features.userProfile.dto.UserProfileResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void toEntity(PatchUserProfileRequest patchUserProfile, @MappingTarget UserProfile userProfile);
  UserProfileResponse toResponse(UserProfile userProfile,String firstName,String lastname,String phoneNumber);
}
