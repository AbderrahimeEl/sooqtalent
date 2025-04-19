package net.elm.sooqtalent.freelancer;

import net.elm.sooqtalent.client.ClientProfile;
import net.elm.sooqtalent.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FreelancerProfileReppository extends JpaRepository<FreelancerProfile,Long> {
    Optional<FreelancerProfile> findByUser(User user);

}
