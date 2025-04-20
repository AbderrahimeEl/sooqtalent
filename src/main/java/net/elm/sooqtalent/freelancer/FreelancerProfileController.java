package net.elm.sooqtalent.freelancer;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/freelancers")
@AllArgsConstructor
public class FreelancerProfileController {
    private final FreelancerProfileService freelancerProfileService;

    @PostMapping("/{userId}/profile")
    public ResponseEntity<FreelancerProfileDTO> createProfile(@PathVariable Long userId, @RequestBody FreelancerProfileDTO profileDTO){
        System.out.println(">>> CREATE PROFILE endpoint called with userId=" + userId);
        FreelancerProfileDTO created = freelancerProfileService.createProfile(userId,profileDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<FreelancerProfileDTO> getProfile(@PathVariable Long userId){
        FreelancerProfileDTO profile = freelancerProfileService.getProfile(userId);
        return new ResponseEntity<>(profile,HttpStatus.OK);
    }
}

