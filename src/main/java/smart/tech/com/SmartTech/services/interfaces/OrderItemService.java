package smart.tech.com.SmartTech.services.interfaces;

import smart.tech.com.SmartTech.model.DTO.OrderItemDTO;
import smart.tech.com.SmartTech.model.domain.OrderItem;

public interface OrderItemService {

    // TODO: kako da stavam orderId pri kreiranje i odma da gi prikaze na order

    OrderItem createOrderItem(OrderItemDTO orderItemDTO);

}
