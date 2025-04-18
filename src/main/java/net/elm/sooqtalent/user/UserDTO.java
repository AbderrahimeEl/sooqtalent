package net.elm.sooqtalent.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private Role role;
}
