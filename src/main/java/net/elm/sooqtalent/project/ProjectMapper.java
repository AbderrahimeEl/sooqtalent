package net.elm.sooqtalent.project;

import java.time.LocalDateTime;

public class ProjectMapper {
    public static ProjectDTO toDTO(Project project) {
        return ProjectDTO.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .category(project.getCategory())
                .budget(project.getBudget())
                .deadline(project.getDeadline())
                .createdAt(project.getCreatedAt())
                .build();
    }

    public static Project toEntity(ProjectDTO dto) {
        return Project.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .budget(dto.getBudget())
                .deadline(dto.getDeadline())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
