package net.elm.sooqtalent.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientProfileController {

    private final ClientProfileService clientProfileService;

    @PostMapping("/{userId}/profile")
    public ResponseEntity<ClientProfileDTO> createProfile(@PathVariable Long userId,
                                                          @RequestBody ClientProfileDTO profileDTO) {
        System.out.println(">>> CREATE PROFILE endpoint called with userId=" + userId);
        ClientProfileDTO created = clientProfileService.createProfile(userId, profileDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<ClientProfileDTO> getProfile(@PathVariable Long userId) {
        ClientProfileDTO profile = clientProfileService.getProfile(userId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
}
