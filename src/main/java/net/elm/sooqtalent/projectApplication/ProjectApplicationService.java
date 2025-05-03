package net.elm.sooqtalent.projectApplication;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.elm.sooqtalent.exception.*;
import net.elm.sooqtalent.freelancer.FreelancerProfile;
import net.elm.sooqtalent.freelancer.FreelancerProfileRepository;
import net.elm.sooqtalent.project.Project;
import net.elm.sooqtalent.project.ProjectRepository;
import net.elm.sooqtalent.project.ProjectStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProjectApplicationService {
    private final ProjectApplicationRepository applicationRepo;
    private final ProjectRepository projectRepo;
    private final FreelancerProfileRepository freelancerRepo;

    @Transactional
    public ProjectApplicationResponse applyToProject(ProjectApplicationRequest request, Long freelancerId) {
        Project project = projectRepo.findById(request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        if(project.getStatus() != ProjectStatus.OPEN) {
            throw new RuntimeException("Project is no longer accepting applications");
        }

        FreelancerProfile freelancer = freelancerRepo.findById(freelancerId)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer not found"));

        if (applicationRepo.existsByProjectAndFreelancer(project, freelancer)) {
            throw new RuntimeException("Already applied to this project");
        }

        ProjectApplication application = ProjectApplication.builder()
                .proposal(request.getProposal())
                .project(project)
                .freelancer(freelancer)
                .build();

        ProjectApplication savedApplication = applicationRepo.save(application);
        return ProjectApplicationMapper.toResponse(savedApplication);
    }

    public ProjectApplicationResponse getApplicationById(Long id) {
        return applicationRepo.findById(id)
                .map(ProjectApplicationMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));
    }

    public Page<ProjectApplicationResponse> getApplicationsByProject(Long projectId, Pageable pageable) {
        return applicationRepo.findAllByProjectId(projectId, pageable)
                .map(ProjectApplicationMapper::toResponse);
    }

    public Page<ProjectApplicationResponse> getApplicationsByFreelancer(Long freelancerId, Pageable pageable) {
        return applicationRepo.findAllByFreelancerId(freelancerId, pageable)
                .map(ProjectApplicationMapper::toResponse);
    }

    @Transactional
    public ProjectApplicationResponse updateApplicationStatus(Long id, ApplicationStatus status) {
        ProjectApplication application = applicationRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        if(application.getProject().getStatus() != ProjectStatus.OPEN) {
            throw new IllegalStateException("Cannot modify applications for closed projects");
        }

        application.setStatus(status);
        return ProjectApplicationMapper.toResponse(applicationRepo.save(application));
    }

    @Transactional
    public void deleteApplication(Long id) {
        if(!applicationRepo.existsById(id)) {
            throw new ResourceNotFoundException("Application not found");
        }
        applicationRepo.deleteById(id);
    }
}