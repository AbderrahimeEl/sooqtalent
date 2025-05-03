package net.elm.sooqtalent.projectApplication;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
public class ProjectApplicationController {

    private final ProjectApplicationService applicationService;

    public ProjectApplicationController(ProjectApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/freelancers/{freelancerId}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ProjectApplicationResponse> applyToProject(
            @PathVariable Long freelancerId,
            @Valid @RequestBody ProjectApplicationRequest request) {
        ProjectApplicationResponse response = applicationService.applyToProject(request, freelancerId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ProjectApplicationResponse> getApplication(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    @GetMapping("/projects/{projectId}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Page<ProjectApplicationResponse>> getProjectApplications(
            @PathVariable Long projectId,
            @PageableDefault(size = 10, sort = "appliedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(applicationService.getApplicationsByProject(projectId,pageable));
    }

    @GetMapping("/freelancers/{freelancerId}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Page<ProjectApplicationResponse>> getFreelancerApplications(
            @PathVariable Long freelancerId,
            @PageableDefault(size = 10, sort = "appliedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(applicationService.getApplicationsByFreelancer(freelancerId, pageable));
    }

    @PatchMapping("/{id}/status")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ProjectApplicationResponse> updateApplicationStatus(
            @PathVariable Long id,
            @RequestParam ApplicationStatus status) {
        return ResponseEntity.ok(applicationService.updateApplicationStatus(id, status));
    }
}