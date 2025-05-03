package net.elm.sooqtalent.client;

import net.elm.sooqtalent.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientProfileRepository extends JpaRepository<ClientProfile, Long> {
    Optional<ClientProfile> findByUser(User user);
    Optional<ClientProfile> findByUserId(Long clientUserId);
    boolean existsByUser(User user);
}
