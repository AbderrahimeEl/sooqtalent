package net.elm.sooqtalent.userprofile;

public class UserProfileMapper {

    public static UserProfileDTO toDTO(UserProfile userProfile) {
        return UserProfileDTO.builder()
                .firstName(userProfile.getFirstName())
                .lastName(userProfile.getLastName())
                .bio(userProfile.getBio())
                .phone(userProfile.getPhone())
                .location(userProfile.getLocation())
                .profilePictureUrl(userProfile.getProfilePictureUrl())
                .build();
    }

    public static UserProfile toEntity(UserProfileDTO dto) {
        return UserProfile.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .bio(dto.getBio())
                .phone(dto.getPhone())
                .location(dto.getLocation())
                .profilePictureUrl(dto.getProfilePictureUrl())
                .build();
    }
}
