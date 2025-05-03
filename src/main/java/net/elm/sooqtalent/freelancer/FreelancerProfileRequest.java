package net.elm.sooqtalent.freelancer;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
public class FreelancerProfileRequest {
    @NotBlank(message = "Professional title is required")
    @Size(max = 100)
    private String title;

    @NotEmpty(message = "At least one skill is required")
    private Set<@NotBlank String> skills;

    @Size(max = 500)
    private String education;

    @Size(max = 1000)
    private String certifications;

    @Pattern(regexp = "^https?:\\/\\/(www\\.)?github\\.com\\/.+", message = "Invalid GitHub URL")
    private String githubUrl;

    @NotNull
    @DecimalMin(value = "0.0", message = "Hourly rate must be positive")
    private BigDecimal hourlyRate;
}