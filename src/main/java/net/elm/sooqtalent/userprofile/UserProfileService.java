package net.elm.sooqtalent.userprofile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.elm.sooqtalent.user.User;
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


    UserProfileDTO updateUserProfile(Long userId, UserProfileDTO profileDTO){
        return null;
    }

    UserProfileDTO getUserProfileByUserId(Long userId){
        return null;
    }

    void deleteUserProfile(Long userId){}
}
