package net.elm.sooqtalent.client;

import jakarta.persistence.*;
import lombok.*;
import net.elm.sooqtalent.user.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String companyWebsite;
    private String industry;

    @Column(length = 1000)
    private String description;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
