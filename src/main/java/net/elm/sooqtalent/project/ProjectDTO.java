package net.elm.sooqtalent.project;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private double budget;
    private Long clientId;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;
    private List<Long> freelancerIds;
}
