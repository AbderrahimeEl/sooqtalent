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

    @PostMapping("/{userId}/profile")
    public ResponseEntity<UserProfileDTO> createProfile(@PathVariable Long userId, @RequestBody UserProfileDTO profileDTO) {
        UserProfileDTO createdUserProfile = userProfileService.createUserProfile(userId,profileDTO);
        return new ResponseEntity<>(createdUserProfile, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/profile")
    public ResponseEntity<UserProfileDTO> updateProfile(@PathVariable Long userId, @RequestBody UserProfileDTO profileDTO) {
        return null;
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserProfileDTO> getProfile(@PathVariable Long userId) {
        return null;
    }

    @DeleteMapping("/{userId}/profile")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long userId) {
        return null;
    }


}
