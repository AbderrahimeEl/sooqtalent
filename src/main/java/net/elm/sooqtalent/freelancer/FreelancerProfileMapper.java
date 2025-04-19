package net.elm.sooqtalent.freelancer;

public class FreelancerProfileMapper {
    public static FreelancerProfileDTO toDTO(FreelancerProfile profile) {
        return FreelancerProfileDTO.builder()
                .title(profile.getTitle())
                .skills(profile.getSkills())
                .education(profile.getEducation())
                .certifications(profile.getCertifications())
                .githubUrl(profile.getGithubUrl())
                .build();
    }
    public static FreelancerProfile toEntity(FreelancerProfileDTO dto) {
        return FreelancerProfile.builder()
                .skills(dto.getSkills())
                .title(dto.getTitle())
                .education(dto.getEducation())
                .certifications(dto.getCertifications())
                .githubUrl(dto.getGithubUrl())
                .build();
    }
}