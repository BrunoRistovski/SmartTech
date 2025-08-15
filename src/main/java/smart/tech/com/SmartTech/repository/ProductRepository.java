package smart.tech.com.SmartTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smart.tech.com.SmartTech.model.domain.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
