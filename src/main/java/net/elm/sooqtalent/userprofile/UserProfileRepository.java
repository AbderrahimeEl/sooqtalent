package net.elm.sooqtalent.userprofile;

import net.elm.sooqtalent.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {
    Optional<UserProfile> findByUserId(Long userId);

    boolean existsByUser(User user);
}
