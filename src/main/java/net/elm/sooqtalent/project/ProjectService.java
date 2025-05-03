package net.elm.sooqtalent.project;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.elm.sooqtalent.category.Category;
import net.elm.sooqtalent.category.CategoryRepository;
import net.elm.sooqtalent.client.ClientProfile;
import net.elm.sooqtalent.client.ClientProfileRepository;
import net.elm.sooqtalent.exception.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepo;
    private final ClientProfileRepository clientRepo;
    private final CategoryRepository categoryRepo;

    @Transactional
    public ProjectResponse createProject(ProjectRequest request, Long clientId) {
        ClientProfile client = clientRepo.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        Category category = categoryRepo.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Project project = Project.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .category(category)
                .budget(request.getBudget())
                .deadline(request.getDeadline())
                .client(client)
                .build();

        return ProjectMapper.toResponse(projectRepo.save(project));
    }

    public ProjectResponse getProjectById(Long id) {
        return projectRepo.findById(id)
                .map(ProjectMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
    }

    public Page<ProjectResponse> getAllProjects(ProjectStatus status, Pageable pageable) {
        return projectRepo.findAllByStatus(status, pageable)
                .map(ProjectMapper::toResponse);
    }

    @Transactional
    public ProjectResponse updateProjectStatus(Long id, ProjectStatus status) {
        Project project = projectRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        if(project.getStatus() == ProjectStatus.COMPLETED) {
            throw new IllegalStateException("Cannot modify completed projects");
        }

        project.setStatus(status);
        return ProjectMapper.toResponse(projectRepo.save(project));
    }

    @Transactional
    public void deleteProject(Long id) {
        if(!projectRepo.existsById(id)) {
            throw new ResourceNotFoundException("Project not found");
        }
        projectRepo.deleteById(id);
    }

    @Transactional
    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        Project project = projectRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        Category category = categoryRepo.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setCategory(category);
        project.setBudget(request.getBudget());
        project.setDeadline(request.getDeadline());

        return ProjectMapper.toResponse(projectRepo.save(project));
    }
}