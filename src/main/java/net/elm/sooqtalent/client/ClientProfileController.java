package net.elm.sooqtalent.client;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ClientProfileController {

    private final ClientProfileService clientProfileService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/users/{userId}/client-profiles")
    public ResponseEntity<ClientProfileResponse> createClientProfile(
            @PathVariable Long userId,
            @Valid @RequestBody ClientProfileRequest request) {
        ClientProfileResponse response = clientProfileService.createProfile(request, userId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/client-profiles/{id}")
    public ResponseEntity<ClientProfileResponse> getClientProfile(@PathVariable Long id) {
        return ResponseEntity.ok(clientProfileService.getProfile(id));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/users/{userId}/client-profile")
    public ResponseEntity<ClientProfileResponse> getClientProfileByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(clientProfileService.getProfileByUserId(userId));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/client-profiles/{id}")
    public ResponseEntity<ClientProfileResponse> updateClientProfile(
            @PathVariable Long id,
            @Valid @RequestBody ClientProfileRequest request) {
        return ResponseEntity.ok(clientProfileService.updateProfile(id, request));
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/client-profiles/{id}")
    public ResponseEntity<Void> deleteClientProfile(@PathVariable Long id) {
        clientProfileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}