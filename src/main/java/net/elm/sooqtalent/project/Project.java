package net.elm.sooqtalent.project;

import jakarta.persistence.*;
import lombok.*;
import net.elm.sooqtalent.category.Category;
import net.elm.sooqtalent.client.ClientProfile;
import net.elm.sooqtalent.projectApplication.ProjectApplication;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(precision = 10, scale = 2) // e.g., 99999999.99
    private BigDecimal budget;

    private LocalDateTime deadline;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status = ProjectStatus.OPEN;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientProfile client;

    @Builder.Default
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<ProjectApplication> applications = new HashSet<>();

}