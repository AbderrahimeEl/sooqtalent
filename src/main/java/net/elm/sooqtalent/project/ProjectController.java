package net.elm.sooqtalent.project;

import lombok.RequiredArgsConstructor;
import net.elm.sooqtalent.freelancer.FreelancerProfileDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/client/{clientId}")
    public ResponseEntity<ProjectDTO> createProject(
            @PathVariable Long clientId,
            @RequestBody ProjectDTO dto) {
        return ResponseEntity.ok(projectService.createProject(clientId, dto));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<ProjectDTO>> getClientProjects(@PathVariable Long clientId) {
        return ResponseEntity.ok(projectService.getProjectsByClient(clientId));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.getById(projectId));
    }

    @PostMapping("/{projectId}/freelancers/{freelancerId}")
    public ResponseEntity<String> assignFreelancerToProject(
            @PathVariable Long projectId,
            @PathVariable Long freelancerId) {
        projectService.assignFreelancerToProject(projectId, freelancerId);
        return ResponseEntity.ok("Freelancer successfully assigned to project");
    }
    @DeleteMapping("/{projectId}/freelancers/{freelancerId}")
    public ResponseEntity<String> removeFreelancerFromProject(
            @PathVariable Long projectId,
            @PathVariable Long freelancerId) {
        projectService.removeFreelancerFromProject(projectId, freelancerId);
        return ResponseEntity.ok("Freelancer successfully removed from project");
    }
    @GetMapping("/{projectId}/freelancers")
    public ResponseEntity<Set<FreelancerProfileDTO>> getProjectFreelancers(
            @PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.getProjectFreelancers(projectId));
    }
    @GetMapping("/freelancers/{freelancerId}")
    public ResponseEntity<Set<ProjectDTO>> getFreelancerProjects(
            @PathVariable Long freelancerId) {
        return ResponseEntity.ok(projectService.getFreelancerProjects(freelancerId));
    }
}
