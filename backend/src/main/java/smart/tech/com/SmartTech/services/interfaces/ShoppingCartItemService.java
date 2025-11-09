package smart.tech.com.SmartTech.services.interfaces;

import smart.tech.com.SmartTech.model.DTO.ShoppingCartItemDTO;
import smart.tech.com.SmartTech.model.domain.ShoppingCartItem;

import java.util.Optional;

public interface ShoppingCartItemService {

    Optional<ShoppingCartItem> findShoppingCartItemById(Long id);

    Optional<ShoppingCartItem> createShoppingCartItem(ShoppingCartItemDTO shoppingCartItemDTO);

    void deleteShoppingCartItem(Long shoppingCartItemId);

}
