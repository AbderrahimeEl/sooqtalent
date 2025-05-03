package net.elm.sooqtalent.freelancer;

import net.elm.sooqtalent.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FreelancerProfileRepository extends JpaRepository<FreelancerProfile,Long> {
    Optional<FreelancerProfile> findByUser(User user);
    @EntityGraph(attributePaths = {"projects"})
    Optional<FreelancerProfile> findWithProjectsById(Long freelancerId);
    Optional<FreelancerProfile> findByUserId(Long userId);
    boolean existsByUser(User user);

}
