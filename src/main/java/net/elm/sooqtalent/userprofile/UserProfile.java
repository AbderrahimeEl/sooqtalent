package net.elm.sooqtalent.userprofile;

import jakarta.persistence.*;
import lombok.*;
import net.elm.sooqtalent.user.User;

import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String bio;

    @ElementCollection
    private List<String> skills;

    private String experience;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
