package net.elm.sooqtalent.freelancer;

import jakarta.persistence.*;
import lombok.*;
import net.elm.sooqtalent.user.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreelancerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String skills;
    private String education;
    private String certifications;
    private String githubUrl;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}