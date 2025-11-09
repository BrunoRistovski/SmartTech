package smart.tech.com.SmartTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smart.tech.com.SmartTech.model.domain.OrderItem;


public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
