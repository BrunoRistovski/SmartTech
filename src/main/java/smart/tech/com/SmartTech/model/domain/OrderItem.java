package smart.tech.com.SmartTech.model.domain;

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
@Table(name = "order_items")

public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    private Double priceOfProductAndQuantity;

    public OrderItem(Order order, Product product, Integer quantity, Double priceOfProductAndQuantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.priceOfProductAndQuantity = priceOfProductAndQuantity;
    }
}