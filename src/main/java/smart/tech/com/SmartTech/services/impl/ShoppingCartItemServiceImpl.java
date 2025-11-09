package smart.tech.com.SmartTech.services.impl;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart.tech.com.SmartTech.model.DTO.ShoppingCartItemDTO;
import smart.tech.com.SmartTech.model.domain.Product;
import smart.tech.com.SmartTech.model.domain.ShoppingCart;
import smart.tech.com.SmartTech.model.domain.ShoppingCartItem;
import smart.tech.com.SmartTech.model.exceptions.ProductNotFoundException;
import smart.tech.com.SmartTech.model.exceptions.QuantityException;
import smart.tech.com.SmartTech.model.exceptions.ShoppingCartItemNotFoundException;
import smart.tech.com.SmartTech.model.exceptions.ShoppingCartNotFoundException;
import smart.tech.com.SmartTech.repository.ProductRepository;
import smart.tech.com.SmartTech.repository.ShoppingCartItemRepository;
import smart.tech.com.SmartTech.repository.ShoppingCartRepository;
import smart.tech.com.SmartTech.services.interfaces.ShoppingCartItemService;

import java.util.Optional;

@Service
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final ProductRepository productRepository;

    public ShoppingCartItemServiceImpl(ShoppingCartRepository shoppingCartRepository, ShoppingCartItemRepository shoppingCartItemRepository, ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingCartItemRepository = shoppingCartItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Optional<ShoppingCartItem> findShoppingCartItemById(Long id) {
        return shoppingCartItemRepository.findById(id);
    }

    @Transactional
    @Override
    public Optional<ShoppingCartItem> createShoppingCartItem(ShoppingCartItemDTO  shoppingCartItemDTO) {

        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartItemDTO.getShoppingCartId()).orElseThrow(ShoppingCartNotFoundException::new);
        Product product = productRepository.findById(shoppingCartItemDTO.getProductId()).orElseThrow(ProductNotFoundException::new);

        Double productPrice = product.getPrice();
        Double totalPrice = productPrice * shoppingCartItemDTO.getQuantity();

        if(shoppingCartItemDTO.getQuantity() > product.getStockQuantity())
            throw new QuantityException();

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(shoppingCart,product,shoppingCartItemDTO.getQuantity(),totalPrice);

        //updating shopping car total amount

        Double shoppingCartTotalAmount = shoppingCart.getTotalAmount() + totalPrice;
        shoppingCart.setTotalAmount(shoppingCartTotalAmount);
        shoppingCartRepository.save(shoppingCart);

        shoppingCartItemRepository.save(shoppingCartItem);

        return Optional.of(shoppingCartItem);
    }

    @Transactional
    @Override
    public void deleteShoppingCartItem(Long shoppingCartItemId) {
        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findById(shoppingCartItemId).orElseThrow(ShoppingCartItemNotFoundException::new);
        ShoppingCart shoppingCart = shoppingCartItem.getShoppingCart();
        shoppingCart.setTotalAmount(shoppingCart.getTotalAmount() - shoppingCartItem.getPriceOfProductAndQuantity());
        shoppingCartItemRepository.delete(shoppingCartItem);

    }
}
