package net.elm.sooqtalent.client;

public class ClientProfileMapper {

    public static ClientProfileResponse toResponse(ClientProfile profile) {
        return ClientProfileResponse.builder()
                .id(profile.getId())
                .companyName(profile.getCompanyName())
                .companyWebsite(profile.getCompanyWebsite())
                .industry(profile.getIndustry())
                .description(profile.getDescription())
                .createdAt(profile.getCreatedAt())
                .updatedAt(profile.getUpdatedAt())
                .userId(profile.getUser().getId())
                .build();
    }

    public static ClientProfile toEntity(ClientProfileRequest request) {
        return ClientProfile.builder()
                .companyName(request.getCompanyName())
                .companyWebsite(request.getCompanyWebsite())
                .industry(request.getIndustry())
                .description(request.getDescription())
                .build();
    }
}