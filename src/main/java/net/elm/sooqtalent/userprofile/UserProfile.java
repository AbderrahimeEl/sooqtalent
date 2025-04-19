package net.elm.sooqtalent.userprofile;

import jakarta.persistence.*;
import lombok.*;
import net.elm.sooqtalent.user.User;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String bio;
    private String phone;
    private String location;
    private String profilePictureUrl;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
