package smart.tech.com.SmartTech.services.interfaces;

import smart.tech.com.SmartTech.model.DTO.OrderDTO;
import smart.tech.com.SmartTech.model.domain.Order;
import smart.tech.com.SmartTech.model.enumerations.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order findById(long orderId);

    Order createOrder (OrderDTO orderDTO);

    Order submitOrder(Long orderId);

    Order cancelOrder(Long orderId);
}
