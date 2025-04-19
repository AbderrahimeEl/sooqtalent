package net.elm.sooqtalent.user.dto;

import lombok.Builder;
import lombok.Data;
import net.elm.sooqtalent.user.Role;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String email;
    private Role role;
}
