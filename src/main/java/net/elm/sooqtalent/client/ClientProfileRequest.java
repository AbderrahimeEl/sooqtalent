// ClientProfileRequest.java (For create/update)
package net.elm.sooqtalent.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientProfileRequest {
    @NotBlank(message = "Company name is required")
    @Size(max = 100)
    private String companyName;

    @Size(max = 255)
    private String companyWebsite;

    @NotBlank(message = "Industry is required")
    @Size(max = 100)
    private String industry;

    @Size(max = 1000)
    private String description;
}
