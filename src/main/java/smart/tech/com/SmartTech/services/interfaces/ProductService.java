package smart.tech.com.SmartTech.services.interfaces;

import smart.tech.com.SmartTech.model.DTO.ProductDTO;
import smart.tech.com.SmartTech.model.enumerations.Category;
import smart.tech.com.SmartTech.model.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getProducts();

    Optional<Product> getProduct(Long productId);

    Optional<Product> createProduct(ProductDTO productDTO);

    Optional<Product> updateProduct(Long productId, ProductDTO productDTO);

    Optional<Product> deleteProduct(Long productId);

}
