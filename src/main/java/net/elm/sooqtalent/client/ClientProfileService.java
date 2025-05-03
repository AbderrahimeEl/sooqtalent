package net.elm.sooqtalent.client;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.elm.sooqtalent.exception.ProfileExistsException;
import net.elm.sooqtalent.exception.ResourceNotFoundException;
import net.elm.sooqtalent.exception.UnauthorizedRoleException;
import net.elm.sooqtalent.user.Role;
import net.elm.sooqtalent.user.User;
import net.elm.sooqtalent.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientProfileService {
    private final ClientProfileRepository clientProfileRepo;
    private final UserRepository userRepo;

    @Transactional
    public ClientProfileResponse createProfile(ClientProfileRequest request, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if(user.getRole() != Role.CLIENT) {
            throw new UnauthorizedRoleException("User must have CLIENT role to create profile");
        }

        if(clientProfileRepo.existsByUser(user)) {
            throw new ProfileExistsException("Client profile already exists for this user");
        }

        ClientProfile profile = ClientProfileMapper.toEntity(request);
        profile.setUser(user);
        return ClientProfileMapper.toResponse(clientProfileRepo.save(profile));
    }

    public ClientProfileResponse getProfile(Long id) {
        return clientProfileRepo.findById(id)
                .map(ClientProfileMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
    }

    public ClientProfileResponse getProfileByUserId(Long userId) {
        return clientProfileRepo.findByUserId(userId)
                .map(ClientProfileMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found for user"));
    }

    @Transactional
    public ClientProfileResponse updateProfile(Long id, ClientProfileRequest request) {
        ClientProfile profile = clientProfileRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        profile.setCompanyName(request.getCompanyName());
        profile.setCompanyWebsite(request.getCompanyWebsite());
        profile.setIndustry(request.getIndustry());
        profile.setDescription(request.getDescription());

        return ClientProfileMapper.toResponse(clientProfileRepo.save(profile));
    }

    @Transactional
    public void deleteProfile(Long id) {
        if(!clientProfileRepo.existsById(id)) {
            throw new ResourceNotFoundException("Profile not found");
        }
        clientProfileRepo.deleteById(id);
    }
}