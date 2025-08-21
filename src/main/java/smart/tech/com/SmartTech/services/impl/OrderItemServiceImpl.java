package smart.tech.com.SmartTech.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart.tech.com.SmartTech.model.DTO.OrderItemDTO;
import smart.tech.com.SmartTech.model.domain.Order;
import smart.tech.com.SmartTech.model.domain.OrderItem;
import smart.tech.com.SmartTech.model.domain.Product;
import smart.tech.com.SmartTech.model.exceptions.OrderNotFoundException;
import smart.tech.com.SmartTech.model.exceptions.ProductNotFoundException;
import smart.tech.com.SmartTech.model.exceptions.QuantityException;
import smart.tech.com.SmartTech.repository.OrderItemRepository;
import smart.tech.com.SmartTech.repository.OrderRepository;
import smart.tech.com.SmartTech.repository.ProductRepository;
import smart.tech.com.SmartTech.services.interfaces.OrderItemService;

import java.util.Optional;

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
    public Optional<OrderItem> createOrderItem(OrderItemDTO orderItemDTO) {

        Order order = orderRepository.findById(orderItemDTO.getOrderId()).orElseThrow(OrderNotFoundException::new);
        Product product = productRepository.findById(orderItemDTO.getProductId()).orElseThrow(ProductNotFoundException::new);

        Double price = product.getPrice();
        Double totalPrice = product.getPrice() * orderItemDTO.getQuantity();

        if(orderItemDTO.getQuantity() > product.getStockQuantity())
            throw new QuantityException();

        OrderItem orderItem = new OrderItem(order,product,orderItemDTO.getQuantity(),totalPrice);

        //updating totalAmount for Order.
        Double orderTotalPrice = order.getTotalAmount() + totalPrice;
        order.setTotalAmount(orderTotalPrice);
        orderRepository.save(order);

        orderItemRepository.save(orderItem);

        return Optional.of(orderItem);
    }

}
