package net.elm.sooqtalent.project;

import lombok.RequiredArgsConstructor;
import net.elm.sooqtalent.client.ClientProfileRepository;
import net.elm.sooqtalent.exception.ResourceNotFoundException;
import net.elm.sooqtalent.freelancer.FreelancerProfile;
import net.elm.sooqtalent.freelancer.FreelancerProfileDTO;
import net.elm.sooqtalent.freelancer.FreelancerProfileMapper;
import net.elm.sooqtalent.freelancer.FreelancerProfileRepository;
import net.elm.sooqtalent.user.UserRepository;
import net.elm.sooqtalent.userprofile.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ClientProfileRepository clientProfileRepository;
    private final FreelancerProfileRepository freelancerProfileRepository;

    public ProjectDTO createProject(Long clientId, ProjectDTO dto) {
        var user = clientProfileRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        var project = ProjectMapper.toEntity(dto);
        project.setClient(user);
        var saved = projectRepository.save(project);

        return ProjectMapper.toDTO(saved);
    }

    public List<ProjectDTO> getProjectsByClient(Long clientId) {
        var client = clientProfileRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        return projectRepository.findByClient(client).stream()
                .map(ProjectMapper::toDTO)
                .toList();
    }

    public ProjectDTO getById(Long projectId) {
        return projectRepository.findById(projectId)
                .map(ProjectMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public void assignFreelancerToProject(Long projectId, Long freelancerId) {
        Project project = projectRepository.findWithFreelancersById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", projectId));

        FreelancerProfile freelancer = freelancerProfileRepository.findById(freelancerId)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer", freelancerId));

        if (project.getFreelancers().contains(freelancer)) {
            throw new ResourceNotFoundException("Freelancer is already assigned to this project");
        }

        project.addFreelancer(freelancer);
        projectRepository.save(project);
    }

    public void removeFreelancerFromProject(Long projectId, Long freelancerId) {
        Project project = projectRepository.findWithFreelancersById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", projectId));

        FreelancerProfile freelancer = freelancerProfileRepository.findById(freelancerId)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer", freelancerId));

        if (!project.getFreelancers().contains(freelancer)) {
            throw new ResourceNotFoundException("Freelancer is not assigned to this project");
        }

        project.removeFreelancer(freelancer);
        projectRepository.save(project);
    }

    public Set<FreelancerProfileDTO> getProjectFreelancers(Long projectId) {
        Project project = projectRepository.findWithFreelancersById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", projectId));

        return project.getFreelancers().stream()
                .map(FreelancerProfileMapper::toDTO)
                .collect(Collectors.toSet());
    }

    public Set<ProjectDTO> getFreelancerProjects(Long freelancerId) {
        FreelancerProfile freelancer = freelancerProfileRepository.findWithProjectsById(freelancerId)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer", freelancerId));

        return freelancer.getProjects().stream()
                .map(ProjectMapper::toDTO)
                .collect(Collectors.toSet());
    }
}
