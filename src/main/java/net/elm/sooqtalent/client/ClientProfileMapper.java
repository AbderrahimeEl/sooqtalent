package net.elm.sooqtalent.client;


public class ClientProfileMapper {

    public static ClientProfileDTO toDTO(ClientProfile profile) {
        return ClientProfileDTO.builder()
                .companyName(profile.getCompanyName())
                .companyWebsite(profile.getCompanyWebsite())
                .industry(profile.getIndustry())
                .description(profile.getDescription())
                .build();
    }

    public static ClientProfile toEntity(ClientProfileDTO dto) {
        return ClientProfile.builder()
                .companyName(dto.getCompanyName())
                .companyWebsite(dto.getCompanyWebsite())
                .industry(dto.getIndustry())
                .description(dto.getDescription())
                .build();
    }
}