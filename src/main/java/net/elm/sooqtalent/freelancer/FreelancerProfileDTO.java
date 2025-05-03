package net.elm.sooqtalent.freelancer;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
public class FreelancerProfileDTO {
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "At least one skill is required")
    private Set<String> skills; // Skill names (e.g., "Java", "Spring Boot")

    private String education;
    private String certifications;
    private String githubUrl;

    @DecimalMin(value = "0.0", message = "Hourly rate must be positive")
    private BigDecimal hourlyRate;

    private Set<Long> applicationIds; // IDs of ProjectApplications
}