package net.elm.sooqtalent.userprofile;

import lombok.RequiredArgsConstructor;
import net.elm.sooqtalent.user.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserProfileController {
    private final UserProfileService userProfileService;
    private final UserProfileRepository userProfileRepository;

    @PostMapping("/{userId}/profile")
    public ResponseEntity<UserProfileDTO> createProfile(@PathVariable Long userId, @RequestBody UserProfileDTO profileDTO) {
        UserProfileDTO createdUserProfile = userProfileService.createUserProfile(userId,profileDTO);
        return new ResponseEntity<>(createdUserProfile, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/profile")
    public ResponseEntity<UserProfileDTO> updateProfile(@PathVariable Long userId, @RequestBody UserProfileDTO profileDTO) {
        UserProfileDTO updatedProfile = userProfileService.updateUserProfile(userId, profileDTO);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }


    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserProfileDTO> getProfile(@PathVariable Long userId) {
        UserProfileDTO profileDTO = userProfileService.getUserProfileByUserId(userId);
        return new ResponseEntity<>(profileDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/profile")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long userId) {
        return null;
    }


}
