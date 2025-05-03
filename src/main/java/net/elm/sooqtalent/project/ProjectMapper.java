package net.elm.sooqtalent.project;

import net.elm.sooqtalent.projectApplication.ProjectApplication;

import java.util.stream.Collectors;

public class ProjectMapper {

    public static ProjectResponse toResponse(Project project) {
        return ProjectResponse.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .budget(project.getBudget())
                .deadline(project.getDeadline())
                .createdAt(project.getCreatedAt())
                .status(project.getStatus())
                .clientId(project.getClient().getId())
                .applicationIds(project.getApplications().stream()
                        .map(ProjectApplication::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static Project toEntity(ProjectRequest request) {
        return Project.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .budget(request.getBudget())
                .deadline(request.getDeadline())
                .build();
    }
}