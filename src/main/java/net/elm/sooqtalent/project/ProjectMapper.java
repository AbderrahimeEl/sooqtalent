package net.elm.sooqtalent.project;

import net.elm.sooqtalent.freelancer.FreelancerProfile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
                .clientId(getClientId(project))
                .freelancerIds(getFreelancerIds(project))
                .build();
    }

    public static Project toEntity(ProjectDTO dto) {
        return Project.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .budget(dto.getBudget())
                .deadline(dto.getDeadline())
                .createdAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now())
                .build();
    }

    private static List<Long> getFreelancerIds(Project project) {
        if (project.getFreelancers() == null) {
            return List.of();
        }
        return project.getFreelancers().stream()
                .map(FreelancerProfile::getId)
                .collect(Collectors.toList());
    }

    private static Long getClientId(Project project) {
        return project.getClient() != null ? project.getClient().getId() : null;
    }
}