package smart.tech.com.SmartTech.services.impl;

import org.springframework.stereotype.Service;
import smart.tech.com.SmartTech.model.domain.ShoppingCart;
import smart.tech.com.SmartTech.model.exceptions.ShoppingCartNotFoundException;
import smart.tech.com.SmartTech.repository.ShoppingCartRepository;
import smart.tech.com.SmartTech.services.interfaces.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {


    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public ShoppingCart getShoppingCartById(Long shoppingCartId) {
        return shoppingCartRepository.findById(shoppingCartId).orElseThrow(ShoppingCartNotFoundException::new);
    }

}
