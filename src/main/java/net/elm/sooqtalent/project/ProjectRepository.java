package net.elm.sooqtalent.project;

import net.elm.sooqtalent.client.ClientProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @EntityGraph(attributePaths = {"freelancers"})
    Page<Project> findAllByStatus(ProjectStatus status, Pageable pageable);
}
