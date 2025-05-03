package net.elm.sooqtalent.freelancer;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FreelancerProfileController {

    private final FreelancerProfileService freelancerService;

    public FreelancerProfileController(FreelancerProfileService freelancerService) {
        this.freelancerService = freelancerService;
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/users/{userId}/freelancer-profiles")
    public ResponseEntity<FreelancerProfileResponse> createFreelancerProfile(
            @PathVariable Long userId,
            @Valid @RequestBody FreelancerProfileRequest request) {
        FreelancerProfileResponse response = freelancerService.createProfile(request, userId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/freelancer-profiles/{id}")
    public ResponseEntity<FreelancerProfileResponse> getFreelancerProfile(@PathVariable Long id) {
        return ResponseEntity.ok(freelancerService.getProfile(id));
    }

    @GetMapping("/users/{userId}/freelancer-profile")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<FreelancerProfileResponse> getFreelancerProfileByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(freelancerService.getProfileByUserId(userId));
    }

    @PutMapping("/freelancer-profiles/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<FreelancerProfileResponse> updateFreelancerProfile(
            @PathVariable Long id,
            @Valid @RequestBody FreelancerProfileRequest request) {
        return ResponseEntity.ok(freelancerService.updateProfile(id, request));
    }

    @DeleteMapping("/freelancer-profiles/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> deleteFreelancerProfile(@PathVariable Long id) {
        freelancerService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}