package net.elm.sooqtalent.freelancer;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class FreelancerProfileResponse {
    private Long id;
    private String title;
    private Set<String> skills;
    private String education;
    private String certifications;
    private String githubUrl;
    private BigDecimal hourlyRate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;
}