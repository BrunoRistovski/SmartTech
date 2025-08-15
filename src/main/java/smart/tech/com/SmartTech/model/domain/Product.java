package smart.tech.com.SmartTech.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import smart.tech.com.SmartTech.model.Enumerations.Category;

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

    @OneToMany(mappedBy = "product")
    private List<ShoppingCartItem> shoppingCartItems;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;
}
