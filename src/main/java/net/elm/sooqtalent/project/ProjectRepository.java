package net.elm.sooqtalent.project;

import net.elm.sooqtalent.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByClient(User client);
}
