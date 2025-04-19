package net.elm.sooqtalent.userprofile;

public class UserProfileMapper {
    public static UserProfileDTO toDTO(UserProfile userProfile) {
        return UserProfileDTO.builder()
                .firstName(userProfile.getFirstName())
                .lastName(userProfile.getLastName())
                .bio(userProfile.getBio())
                .skills(userProfile.getSkills())
                .experience(userProfile.getExperience())
                .build();
    }
}
