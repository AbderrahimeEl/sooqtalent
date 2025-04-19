package net.elm.sooqtalent.freelancer;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.elm.sooqtalent.user.User;
import net.elm.sooqtalent.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FreelancerProfileService {

    private final FreelancerProfileReppository freelancerProfileReppository;
    private final UserRepository userRepository;

    @Transactional
    public FreelancerProfileDTO createProfile(Long userId, FreelancerProfileDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (freelancerProfileReppository.findByUser(user).isPresent()) {
            throw new RuntimeException("Profile already exists for this user");
        }

        FreelancerProfile profile = FreelancerProfileMapper.toEntity(dto);
        profile.setUser(user);
        return FreelancerProfileMapper.toDTO(freelancerProfileReppository.save(profile));
    }

    public FreelancerProfileDTO getProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return freelancerProfileReppository.findByUser(user)
                .map(FreelancerProfileMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }
}
