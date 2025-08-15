package smart.tech.com.SmartTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smart.tech.com.SmartTech.model.domain.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
}
