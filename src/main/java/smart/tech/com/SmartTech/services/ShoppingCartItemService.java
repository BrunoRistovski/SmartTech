package smart.tech.com.SmartTech.services;

import smart.tech.com.SmartTech.model.domain.Product;
import smart.tech.com.SmartTech.model.domain.ShoppingCartItem;

public interface ShoppingCartItemService {

    ShoppingCartItem createShoppingCartItem(Long shoppingCartId, Long productId, Integer quantity);

    ShoppingCartItem deleteShoppingCartItem(Long shoppingCartItemId);

}
