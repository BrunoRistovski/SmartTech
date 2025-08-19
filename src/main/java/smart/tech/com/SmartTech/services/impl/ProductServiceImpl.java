package smart.tech.com.SmartTech.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart.tech.com.SmartTech.model.domain.OrderItem;
import smart.tech.com.SmartTech.model.domain.Product;
import smart.tech.com.SmartTech.model.domain.ShoppingCartItem;
import smart.tech.com.SmartTech.model.enumerations.Category;
import smart.tech.com.SmartTech.model.exceptions.ProductNotFoundException;
import smart.tech.com.SmartTech.repository.ProductRepository;
import smart.tech.com.SmartTech.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    @Transactional
    @Override
    public Product createProduct(String name, String description, String imageUrl, Category category, Double price, Integer stockQuantity) {

        List<ShoppingCartItem> shoppingCartItemsInProduct = new ArrayList<>();
        List<OrderItem> orderItemsInsideProduct = new ArrayList<>();

        Product product = new Product(name,description,imageUrl,category,price,stockQuantity,shoppingCartItemsInProduct,orderItemsInsideProduct);
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Product updateProduct(Long productId, String name, String description, String imageUrl, Category category, Double price, Integer stockQuantity) {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        product.setName(name);
        product.setDescription(description);
        product.setImageUrl(imageUrl);
        product.setCategory(category);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Product deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
        return product;
    }
}
