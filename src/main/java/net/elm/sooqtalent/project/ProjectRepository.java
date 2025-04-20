package net.elm.sooqtalent.project;

import net.elm.sooqtalent.client.ClientProfile;
import net.elm.sooqtalent.user.User;
import net.elm.sooqtalent.userprofile.UserProfile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByClient(ClientProfile client);
    @EntityGraph(attributePaths = {"freelancers"})
    Optional<Project> findWithFreelancersById(Long projectId);
}
