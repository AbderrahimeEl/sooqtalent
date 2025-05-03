package net.elm.sooqtalent.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class JwtResponse {
    private String token;
}
