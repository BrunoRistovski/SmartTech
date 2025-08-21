package smart.tech.com.SmartTech.services.interfaces;

import smart.tech.com.SmartTech.model.domain.ShoppingCart;

import java.util.Optional;

public interface ShoppingCartService {

    Optional<ShoppingCart> getShoppingCartById(Long shoppingCartId);

}
