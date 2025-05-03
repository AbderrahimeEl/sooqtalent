package net.elm.sooqtalent.projectApplication;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ProjectApplicationResponse {
    private Long id;
    private String proposal;
    private ApplicationStatus status;
    private LocalDateTime appliedAt;
    private LocalDateTime updatedAt;
    private Long projectId;
    private Long freelancerId;
    private String projectTitle;
    private String freelancerName;
}
