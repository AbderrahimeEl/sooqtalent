package net.elm.sooqtalent.projectApplication;


public class ProjectApplicationMapper {

    public static ProjectApplicationResponse toResponse(ProjectApplication application) {
        return ProjectApplicationResponse.builder()
                .id(application.getId())
                .proposal(application.getProposal())
                .status(application.getStatus())
                .appliedAt(application.getAppliedAt())
                .projectId(application.getProject().getId())
                .projectTitle(application.getProject().getTitle())
                .freelancerId(application.getFreelancer().getId())
                .freelancerName(application.getFreelancer().getUser().getFirstName() + " "
                        + application.getFreelancer().getUser().getLastName())
                .build();
    }

    public static ProjectApplication toEntity(ProjectApplicationRequest request) {
        return ProjectApplication.builder()
                .proposal(request.getProposal())
                .build();
    }
}