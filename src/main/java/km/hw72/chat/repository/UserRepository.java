package km.hw72.chat.repository;

import km.hw72.chat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsBySession(String session);

    User findBySession(String session);
}
