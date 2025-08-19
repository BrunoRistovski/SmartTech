package smart.tech.com.SmartTech.services;

import smart.tech.com.SmartTech.model.domain.Order;
import smart.tech.com.SmartTech.model.domain.OrderItem;
import smart.tech.com.SmartTech.model.enumerations.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order findById(long orderId);

    Order createOrder (OrderStatus orderStatus, String address, String city, String zipcode,
                       LocalDateTime createdAt, Double totalAmount, String username);

    Order submitOrder(Long orderId);

    Order cancelOrder(Long orderId);
}
