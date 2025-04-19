package net.elm.sooqtalent.freelancer;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FreelancerProfileDTO {
    private String title;
    private String skills;
    private String education;
    private String certifications;
    private String githubUrl;
}
