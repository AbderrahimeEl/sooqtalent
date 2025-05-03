package net.elm.sooqtalent.project;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ProjectRequest {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Category is required")
    private Long categoryId;

    @DecimalMin(value = "0.0", message = "Budget must be positive")
    private BigDecimal budget;

    @Future(message = "Deadline must be in the future")
    private LocalDateTime deadline;
}