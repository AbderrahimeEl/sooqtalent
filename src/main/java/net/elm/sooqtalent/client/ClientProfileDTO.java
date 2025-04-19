package net.elm.sooqtalent.client;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientProfileDTO {
    private String companyName;
    private String companyWebsite;
    private String industry;
    private String description;
}
