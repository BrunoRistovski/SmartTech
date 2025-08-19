package smart.tech.com.SmartTech.services;

import smart.tech.com.SmartTech.model.domain.OrderItem;

public interface OrderItemService {

    // TODO: kako da stavam orderId pri kreiranje i odma da gi prikaze na order

    OrderItem createOrderItem(Long orderId, Long productId, Integer quantity);

}
