package smart.tech.com.SmartTech.services.interfaces;

import smart.tech.com.SmartTech.model.DTO.ShoppingCartItemDTO;
import smart.tech.com.SmartTech.model.domain.ShoppingCartItem;

public interface ShoppingCartItemService {

    ShoppingCartItem createShoppingCartItem(ShoppingCartItemDTO shoppingCartItemDTO);

    ShoppingCartItem deleteShoppingCartItem(Long shoppingCartItemId);

}
