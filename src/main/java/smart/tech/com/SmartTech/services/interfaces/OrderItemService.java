package smart.tech.com.SmartTech.services.interfaces;

import smart.tech.com.SmartTech.model.DTO.OrderItemDTO;
import smart.tech.com.SmartTech.model.domain.OrderItem;

import java.util.Optional;

public interface OrderItemService {

    // TODO: kako da stavam orderId pri kreiranje i odma da gi prikaze na order

    Optional<OrderItem> createOrderItem(OrderItemDTO orderItemDTO);

}
