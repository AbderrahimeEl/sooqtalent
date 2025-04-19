package net.elm.sooqtalent.project;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
