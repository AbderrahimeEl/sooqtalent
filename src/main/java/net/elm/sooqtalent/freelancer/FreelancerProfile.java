package net.elm.sooqtalent.freelancer;

import jakarta.persistence.*;
import lombok.*;
import net.elm.sooqtalent.user.User;
import net.elm.sooqtalent.project.Project;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreelancerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String skills;
    private String education;
    private String certifications;
    private String githubUrl;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(mappedBy = "freelancers")
    private Set<Project> projects = new HashSet<>();

    public void addProject(Project project) {
        this.projects.add(project);
        project.getFreelancers().add(this);
    }

    public void removeProject(Project project) {
        this.projects.remove(project);
        project.getFreelancers().remove(this);
    }
}