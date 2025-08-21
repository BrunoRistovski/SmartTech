package smart.tech.com.SmartTech.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import smart.tech.com.SmartTech.model.enumerations.Category;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 5000)
    private String description;

    private String imageUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    private Double price;

    private Integer stockQuantity;

    @JsonBackReference
    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    private List<ShoppingCartItem> shoppingCartItems;

    @JsonBackReference
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

    public Product(String name, String description, String imageUrl, Category category, Double price, Integer stockQuantity, List<ShoppingCartItem> shoppingCartItems, List<OrderItem> orderItems) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.shoppingCartItems = shoppingCartItems;
        this.orderItems = orderItems;
    }
}
