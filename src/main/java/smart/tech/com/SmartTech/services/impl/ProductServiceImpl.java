package smart.tech.com.SmartTech.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart.tech.com.SmartTech.model.DTO.ProductDTO;
import smart.tech.com.SmartTech.model.domain.OrderItem;
import smart.tech.com.SmartTech.model.domain.Product;
import smart.tech.com.SmartTech.model.domain.ShoppingCartItem;
import smart.tech.com.SmartTech.model.enumerations.Category;
import smart.tech.com.SmartTech.model.exceptions.ProductNotFoundException;
import smart.tech.com.SmartTech.repository.ProductRepository;
import smart.tech.com.SmartTech.services.interfaces.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Product> getProduct(Long productId) {
        return Optional.of(productRepository.findById(productId).orElseThrow(ProductNotFoundException::new));
    }

    @Transactional
    @Override
    public Optional<Product> createProduct(ProductDTO productDTO) {

        List<ShoppingCartItem> shoppingCartItemsInProduct = new ArrayList<>();
        List<OrderItem> orderItemsInsideProduct = new ArrayList<>();

        Product product = new Product(productDTO.getName(),productDTO.getDescription(),productDTO.getImageUrl(),productDTO.getCategory(),
                productDTO.getPrice(),productDTO.getStockQuantity(),shoppingCartItemsInProduct,orderItemsInsideProduct);

        productRepository.save(product);

        return Optional.of(product);
    }

    @Transactional
    @Override
    public Optional<Product> updateProduct(Long productId, ProductDTO productDTO) {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setImageUrl(productDTO.getImageUrl());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());

        productRepository.save(product);

        return Optional.of(product);
    }

    @Transactional
    @Override
    public Optional<Product> deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
        return Optional.of(product);
    }
}
