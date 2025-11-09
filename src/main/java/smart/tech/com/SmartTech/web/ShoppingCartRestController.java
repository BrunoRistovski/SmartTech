package smart.tech.com.SmartTech.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smart.tech.com.SmartTech.model.DTO.ShoppingCartItemDTO;
import smart.tech.com.SmartTech.model.domain.ShoppingCart;
import smart.tech.com.SmartTech.model.domain.ShoppingCartItem;
import smart.tech.com.SmartTech.services.interfaces.ShoppingCartItemService;
import smart.tech.com.SmartTech.services.interfaces.ShoppingCartService;


@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/shopping_cart")
public class ShoppingCartRestController {

    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartItemService shoppingCartItemService;

    public ShoppingCartRestController(ShoppingCartService shoppingCartService, ShoppingCartItemService shoppingCartItemService) {
        this.shoppingCartService = shoppingCartService;
        this.shoppingCartItemService = shoppingCartItemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable Long id) {
        return shoppingCartService.getShoppingCartById(id)
                .map(shoppingCart -> ResponseEntity.ok().body(shoppingCart))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add_item")
    public ResponseEntity<ShoppingCartItem> addItemToShoppingCart(@RequestBody ShoppingCartItemDTO shoppingCartItemDTO) {
        return shoppingCartItemService.createShoppingCartItem(shoppingCartItemDTO)
                .map(shoppingCartItem -> ResponseEntity.ok().body(shoppingCartItem))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete_item/{id}")
    public ResponseEntity deleteItemFromShoppingCart(@PathVariable Long id) {

        shoppingCartItemService.deleteShoppingCartItem(id);
        if(shoppingCartItemService.findShoppingCartItemById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}
