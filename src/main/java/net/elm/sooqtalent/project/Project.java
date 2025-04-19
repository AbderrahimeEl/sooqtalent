package net.elm.sooqtalent.project;

import jakarta.persistence.*;
import lombok.*;
import net.elm.sooqtalent.user.User;

import java.time.LocalDateTime;

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
    private User client;
}

