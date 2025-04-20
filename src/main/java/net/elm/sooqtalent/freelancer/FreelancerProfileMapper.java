package net.elm.sooqtalent.freelancer;

import net.elm.sooqtalent.project.Project;

import java.util.List;
import java.util.stream.Collectors;

public class FreelancerProfileMapper {
    public static FreelancerProfileDTO toDTO(FreelancerProfile profile) {
        return FreelancerProfileDTO.builder()
                .id(profile.getId())
                .title(profile.getTitle())
                .skills(profile.getSkills())
                .education(profile.getEducation())
                .certifications(profile.getCertifications())
                .githubUrl(profile.getGithubUrl())
                .projectIds(getProjectIds(profile))
                .build();
    }

    public static FreelancerProfile toEntity(FreelancerProfileDTO dto) {
        return FreelancerProfile.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .skills(dto.getSkills())
                .education(dto.getEducation())
                .certifications(dto.getCertifications())
                .githubUrl(dto.getGithubUrl())
                .build();
    }

    private static List<Long> getProjectIds(FreelancerProfile profile) {
        if (profile.getProjects() == null) {
            return List.of();
        }
        return profile.getProjects().stream()
                .map(Project::getId)
                .collect(Collectors.toList());
    }
}