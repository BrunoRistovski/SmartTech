package smart.tech.com.SmartTech.services.interfaces;

import smart.tech.com.SmartTech.model.DTO.ProductDTO;
import smart.tech.com.SmartTech.model.enumerations.Category;
import smart.tech.com.SmartTech.model.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product getProduct(Long productId);

    Product createProduct(ProductDTO productDTO);

    Product updateProduct(Long productId, ProductDTO productDTO);

    Product deleteProduct(Long productId);

}
