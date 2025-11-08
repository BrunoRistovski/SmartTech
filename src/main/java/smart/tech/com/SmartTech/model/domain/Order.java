package smart.tech.com.SmartTech.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import smart.tech.com.SmartTech.model.enumerations.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String zipcode;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime orderDate;

    @Column(nullable = false)
    private Double totalAmount;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "username")
    private User user;

    @JsonBackReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    public Order(OrderStatus status, String address, String city, String zipcode, LocalDateTime createdAt, Double totalAmount, User user, List<OrderItem> orderItems) {
        this.status = status;
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.user = user;
        this.orderItems = orderItems;
    }
}
