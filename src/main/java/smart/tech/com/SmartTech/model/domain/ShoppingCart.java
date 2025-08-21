package smart.tech.com.SmartTech.model.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateCreated;

    @Column(nullable = false)
    private Double totalAmount;

    @JsonBackReference
    @OneToOne(mappedBy = "shoppingCart")
    private User user;

    @JsonBackReference
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingCartItem> shoppingCartItems;

    public ShoppingCart(LocalDateTime dateCreated, Double totalAmount, List<ShoppingCartItem> shoppingCartItems) {
        this.dateCreated = dateCreated;
        this.totalAmount = totalAmount;
        this.shoppingCartItems = shoppingCartItems;
    }
}
