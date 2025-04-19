package net.elm.sooqtalent.project;

import lombok.*;

import java.time.LocalDateTime;

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
    private LocalDateTime deadline;
    private LocalDateTime createdAt;
}
