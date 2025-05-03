package net.elm.sooqtalent.projectApplication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectApplicationRequest {
    @NotBlank(message = "Proposal text is required")
    @Size(max = 2000)
    private String proposal;

    @NotNull(message = "Project ID is required")
    private Long projectId;
}