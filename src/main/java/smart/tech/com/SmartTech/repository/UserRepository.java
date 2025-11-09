package smart.tech.com.SmartTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smart.tech.com.SmartTech.model.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

}
