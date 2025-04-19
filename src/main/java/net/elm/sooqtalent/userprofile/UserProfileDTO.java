package net.elm.sooqtalent.userprofile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor @Getter @Setter @Builder
public class UserProfileDTO {
    private String firstName;
    private String lastName;
    private String bio;
    private List<String> skills;
    private String experience;
}

