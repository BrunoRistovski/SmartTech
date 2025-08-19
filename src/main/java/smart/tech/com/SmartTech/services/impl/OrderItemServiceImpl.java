package smart.tech.com.SmartTech.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart.tech.com.SmartTech.model.domain.Order;
import smart.tech.com.SmartTech.model.domain.OrderItem;
import smart.tech.com.SmartTech.model.domain.Product;
import smart.tech.com.SmartTech.model.exceptions.OrderNotFoundException;
import smart.tech.com.SmartTech.model.exceptions.ProductNotFoundException;
import smart.tech.com.SmartTech.model.exceptions.QuantityException;
import smart.tech.com.SmartTech.repository.OrderItemRepository;
import smart.tech.com.SmartTech.repository.OrderRepository;
import smart.tech.com.SmartTech.repository.ProductRepository;
import smart.tech.com.SmartTech.services.OrderItemService;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public OrderItem createOrderItem(Long orderId, Long productId, Integer quantity) {

        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);

        Double price = product.getPrice();
        Double totalPrice = product.getPrice() * quantity;

        if(quantity > product.getStockQuantity())
            throw new QuantityException();

        OrderItem orderItem = new OrderItem(order,product,quantity,totalPrice);

        //updating totalAmount for Order.
        Double orderTotalPrice = order.getTotalAmount() + totalPrice;
        order.setTotalAmount(orderTotalPrice);
        orderRepository.save(order);

        return orderItemRepository.save(orderItem);
    }

}
