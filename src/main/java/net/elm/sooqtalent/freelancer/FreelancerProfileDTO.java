package net.elm.sooqtalent.freelancer;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FreelancerProfileDTO {
    private Long id;
    private String title;
    private String skills;
    private String education;
    private String certifications;
    private String githubUrl;
    private List<Long> projectIds;
}
