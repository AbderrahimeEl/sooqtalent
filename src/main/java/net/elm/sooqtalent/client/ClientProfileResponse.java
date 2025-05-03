package net.elm.sooqtalent.client;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ClientProfileResponse {
    private Long id;
    private String companyName;
    private String companyWebsite;
    private String industry;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;
}

