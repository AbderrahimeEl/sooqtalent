package net.elm.sooqtalent.project;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.elm.sooqtalent.freelancer.FreelancerProfileDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/clients/{clientId}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ProjectResponse> createProject(
            @PathVariable Long clientId,
            @Valid @RequestBody ProjectRequest request) {
        ProjectResponse response = projectService.createProject(request, clientId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    // To fix
//    @GetMapping
//    public ResponseEntity<List<ProjectResponse>> getAllProjects(
//            @RequestParam(required = false) ProjectStatus status) {
//        Pageable pageable = new Pageable();
//        return ResponseEntity.ok(projectService.getAllProjects(status,pageable));
//    }

    @PatchMapping("/{id}/status")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ProjectResponse> updateProjectStatus(
            @PathVariable Long id,
            @RequestParam ProjectStatus status) {
        return ResponseEntity.ok(projectService.updateProjectStatus(id, status));
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}