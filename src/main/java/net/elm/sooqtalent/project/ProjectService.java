package net.elm.sooqtalent.project;

import lombok.RequiredArgsConstructor;
import net.elm.sooqtalent.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectDTO createProject(Long clientId, ProjectDTO dto) {
        var user = userRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        var project = ProjectMapper.toEntity(dto);
        project.setClient(user);
        var saved = projectRepository.save(project);

        return ProjectMapper.toDTO(saved);
    }

    public List<ProjectDTO> getProjectsByClient(Long clientId) {
        var client = userRepository.findById(clientId)
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
}
