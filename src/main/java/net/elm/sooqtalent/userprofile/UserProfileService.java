package net.elm.sooqtalent.userprofile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.elm.sooqtalent.user.User;
import net.elm.sooqtalent.user.UserMapper;
import net.elm.sooqtalent.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Getter
@Setter
@Builder

public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    public UserProfileDTO createUserProfile(Long userId, UserProfileDTO profileDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Optional<UserProfile> existingProfile = userProfileRepository.findByUserId(userId);
        if (existingProfile.isPresent()) {
            throw new RuntimeException("User already has a profile");
        }
        UserProfile profile = UserProfileMapper.toEntity(profileDTO);
        profile.setUser(user);
        UserProfile savedProfile = userProfileRepository.save(profile);
        return UserProfileMapper.toDTO(savedProfile);
    }


    public UserProfileDTO updateUserProfile(Long userId, UserProfileDTO profileDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User profile not found"));
        profile.setFirstName(profileDTO.getFirstName());
        profile.setLastName(profileDTO.getLastName());
        profile.setBio(profileDTO.getBio());
        profile.setSkills(profileDTO.getSkills());
        profile.setExperience(profileDTO.getExperience());

        UserProfile updatedProfile = userProfileRepository.save(profile);
        return UserProfileMapper.toDTO(updatedProfile);
    }

    public UserProfileDTO getUserProfileByUserId(Long userId) {
        UserProfile userProfile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User profile does not exist"));
        return UserProfileMapper.toDTO(userProfile);
    }

    public void deleteUserProfile(Long userId) {
        UserProfile userProfile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User profile does not exist"));

        userProfileRepository.delete(userProfile);
    }
}
