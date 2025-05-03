package net.elm.sooqtalent.freelancer;

import net.elm.sooqtalent.projectApplication.ProjectApplication;
import net.elm.sooqtalent.skill.Skill;
import java.util.Set;
import java.util.stream.Collectors;

public class FreelancerProfileMapper {

    public static FreelancerProfileResponse toResponse(FreelancerProfile profile) {
        return FreelancerProfileResponse.builder()
                .id(profile.getId())
                .title(profile.getTitle())
                .skills(mapSkills(profile.getSkills()))
                .education(profile.getEducation())
                .certifications(profile.getCertifications())
                .githubUrl(profile.getGithubUrl())
                .hourlyRate(profile.getHourlyRate())
                .createdAt(profile.getCreatedAt())
                .updatedAt(profile.getUpdatedAt())
                .userId(profile.getUser().getId())
                .build();
    }

    public static FreelancerProfile toEntity(FreelancerProfileRequest request) {
        return FreelancerProfile.builder()
                .title(request.getTitle())
                .education(request.getEducation())
                .certifications(request.getCertifications())
                .githubUrl(request.getGithubUrl())
                .hourlyRate(request.getHourlyRate())
                .build();
    }

    private static Set<String> mapSkills(Set<Skill> skills) {
        if (skills == null) return Set.of();
        return skills.stream()
                .map(Skill::getName)
                .collect(Collectors.toSet());
    }

    private static Set<Long> mapApplicationIds(Set<ProjectApplication> applications) {
        if (applications == null) return Set.of();
        return applications.stream()
                .map(ProjectApplication::getId)
                .collect(Collectors.toSet());
    }
}