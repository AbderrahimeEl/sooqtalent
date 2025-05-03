package net.elm.sooqtalent.project;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class ProjectResponse {
    private Long id;
    private String title;
    private String description;
    private String category;
    private BigDecimal budget;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;
    private ProjectStatus status;
    private Long clientId;
    private Set<Long> applicationIds;
}