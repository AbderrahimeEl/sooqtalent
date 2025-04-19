package net.elm.sooqtalent.userprofile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserProfileDTO {
    private String firstName;
    private String lastName;
    private String bio;
    private String phone;
    private String location;
    private String profilePictureUrl;
}
