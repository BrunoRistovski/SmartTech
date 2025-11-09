package smart.tech.com.SmartTech.model.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "shopping_carts_items")

public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    private Double priceOfProductAndQuantity;

    public ShoppingCartItem(ShoppingCart shoppingCart, Product product, Integer quantity, Double priceOfProductAndQuantity) {
        this.shoppingCart = shoppingCart;
        this.product = product;
        this.quantity = quantity;
        this.priceOfProductAndQuantity = priceOfProductAndQuantity;
    }
}
