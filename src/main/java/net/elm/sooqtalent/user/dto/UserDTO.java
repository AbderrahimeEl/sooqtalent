package net.elm.sooqtalent.user.dto;

import lombok.Builder;
import lombok.Data;
import net.elm.sooqtalent.user.Role;
import java.time.LocalDateTime;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String email;
    private Role role;
    private String firstName;
    private String lastName;
    private String phone;
    private String location;
    private String profilePictureUrl;
    private String bio;
    private LocalDateTime createdAt;
}