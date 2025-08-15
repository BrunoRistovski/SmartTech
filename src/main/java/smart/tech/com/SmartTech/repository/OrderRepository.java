package smart.tech.com.SmartTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smart.tech.com.SmartTech.model.domain.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
