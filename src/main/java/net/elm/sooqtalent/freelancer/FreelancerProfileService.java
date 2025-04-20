package net.elm.sooqtalent.freelancer;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.elm.sooqtalent.exception.ResourceNotFoundException;
import net.elm.sooqtalent.project.Project;
import net.elm.sooqtalent.project.ProjectDTO;
import net.elm.sooqtalent.project.ProjectMapper;
import net.elm.sooqtalent.project.ProjectRepository;
import net.elm.sooqtalent.user.User;
import net.elm.sooqtalent.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FreelancerProfileService {

    private final FreelancerProfileRepository freelancerProfileRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public FreelancerProfileDTO createProfile(Long userId, FreelancerProfileDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (freelancerProfileRepository.findByUser(user).isPresent()) {
            throw new ResourceNotFoundException("Profile already exists for this user");
        }

        FreelancerProfile profile = FreelancerProfileMapper.toEntity(dto);
        profile.setUser(user);
        return FreelancerProfileMapper.toDTO(freelancerProfileRepository.save(profile));
    }

    public FreelancerProfileDTO getProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return freelancerProfileRepository.findByUser(user)
                .map(FreelancerProfileMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
    }

    public void assignToProject(Long freelancerId, Long projectId) {
        FreelancerProfile freelancer = freelancerProfileRepository.findById(freelancerId)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer not found"));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        freelancer.addProject(project);
        freelancerProfileRepository.save(freelancer);
    }

    public void removeFromProject(Long freelancerId, Long projectId) {
        FreelancerProfile freelancer = freelancerProfileRepository.findById(freelancerId)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer not found"));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        freelancer.removeProject(project);
        freelancerProfileRepository.save(freelancer);
    }

    public Set<ProjectDTO> getFreelancerProjects(Long freelancerId) {
        FreelancerProfile freelancer = freelancerProfileRepository.findWithProjectsById(freelancerId)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer not found"));
        return freelancer.getProjects().stream()
                .map(ProjectMapper::toDTO)
                .collect(Collectors.toSet());
    }
}
