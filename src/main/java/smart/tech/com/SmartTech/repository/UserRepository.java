package smart.tech.com.SmartTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smart.tech.com.SmartTech.model.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
}
