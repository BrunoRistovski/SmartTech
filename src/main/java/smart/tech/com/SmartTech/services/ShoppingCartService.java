package smart.tech.com.SmartTech.services;

import smart.tech.com.SmartTech.model.domain.ShoppingCart;
import smart.tech.com.SmartTech.model.domain.ShoppingCartItem;

import java.time.LocalDateTime;
import java.util.List;

public interface ShoppingCartService {

    ShoppingCart getShoppingCartById(Long shoppingCartId);

//    ShoppingCart addToShoppingCart(ShoppingCartItem shoppingCartItem);
//
//    ShoppingCart removeFromShoppingCart(ShoppingCartItem shoppingCartItem);


}
