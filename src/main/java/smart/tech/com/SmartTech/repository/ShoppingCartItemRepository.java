package smart.tech.com.SmartTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smart.tech.com.SmartTech.model.domain.ShoppingCartItem;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem,Long> {
}
