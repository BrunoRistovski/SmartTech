package smart.tech.com.SmartTech.services;

import smart.tech.com.SmartTech.model.enumerations.Category;
import smart.tech.com.SmartTech.model.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product getProduct(Long productId);

    Product createProduct(String name, String description, String imageUrl, Category category, Double price, Integer stockQuantity);

    Product updateProduct(Long productId, String name, String description, String imageUrl, Category category, Double price, Integer stockQuantity);

    Product deleteProduct(Long productId);

}
