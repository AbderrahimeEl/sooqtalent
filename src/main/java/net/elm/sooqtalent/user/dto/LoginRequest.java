package net.elm.sooqtalent.user.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username; // this is email actually
    private String password;
}
