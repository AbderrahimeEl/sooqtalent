package net.elm.sooqtalent.client;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.elm.sooqtalent.user.User;
import net.elm.sooqtalent.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientProfileService {

    private final ClientProfileRepository clientProfileRepository;
    private final UserRepository userRepository;

    @Transactional
    public ClientProfileDTO createProfile(Long userId, ClientProfileDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (clientProfileRepository.findByUser(user).isPresent()) {
            throw new RuntimeException("Profile already exists for this user");
        }

        ClientProfile profile = ClientProfileMapper.toEntity(dto);
        profile.setUser(user);
        return ClientProfileMapper.toDTO(clientProfileRepository.save(profile));
    }

    public ClientProfileDTO getProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return clientProfileRepository.findByUser(user)
                .map(ClientProfileMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }
}
