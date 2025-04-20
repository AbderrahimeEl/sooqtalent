package net.elm.sooqtalent.project;

import jakarta.persistence.*;
import lombok.*;
import net.elm.sooqtalent.client.ClientProfile;
import net.elm.sooqtalent.freelancer.FreelancerProfile;
import net.elm.sooqtalent.user.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(length = 1000)
    private String description;

    private String category;

    private double budget;

    private LocalDateTime deadline;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientProfile client;

    @ManyToMany
    @JoinTable(
            name = "project_freelancer",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "freelancer_id")
    )
    private Set<FreelancerProfile> freelancers = new HashSet<>();

    public void addFreelancer(FreelancerProfile freelancer) {
        this.freelancers.add(freelancer);
        freelancer.getProjects().add(this);
    }

    public void removeFreelancer(FreelancerProfile freelancer) {
        this.freelancers.remove(freelancer);
        freelancer.getProjects().remove(this);
    }
}

