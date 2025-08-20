package smart.tech.com.SmartTech.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart.tech.com.SmartTech.model.DTO.OrderDTO;
import smart.tech.com.SmartTech.model.domain.*;
import smart.tech.com.SmartTech.model.enumerations.OrderStatus;
import smart.tech.com.SmartTech.model.exceptions.OrderNotFoundException;
import smart.tech.com.SmartTech.repository.OrderItemRepository;
import smart.tech.com.SmartTech.repository.OrderRepository;
import smart.tech.com.SmartTech.repository.ShoppingCartItemRepository;
import smart.tech.com.SmartTech.repository.UserRepository;
import smart.tech.com.SmartTech.services.interfaces.OrderService;
import smart.tech.com.SmartTech.services.interfaces.ShoppingCartItemService;
import smart.tech.com.SmartTech.services.interfaces.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderItemRepository orderItemRepository;
    private final ShoppingCartItemService shoppingCartItemService;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, OrderItemRepository orderItemRepository, ShoppingCartItemRepository shoppingCartItemRepository, ShoppingCartItemService shoppingCartItemService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.orderItemRepository = orderItemRepository;
        this.shoppingCartItemService = shoppingCartItemService;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(long orderId) {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    @Transactional
    @Override
    public Order createOrder(OrderDTO orderDTO) {

        List<OrderItem> orderItems = new ArrayList<>();
        User user = userService.findByUsername(orderDTO.getUsername());

        Order order = new Order(OrderStatus.CREATED,orderDTO.getAddress(),orderDTO.getCity(),orderDTO.getZipcode(),LocalDateTime.now(),0.0,user,orderItems);
        ShoppingCart shoppingcart = user.getShoppingCart();
        List<ShoppingCartItem>  shoppingCartItems = shoppingcart.getShoppingCartItems();
        for(ShoppingCartItem shoppingCartItem : shoppingCartItems){
            shoppingCartItemService.deleteShoppingCartItem(shoppingCartItem.getId());
        }

        return orderRepository.save(order);
    }

    @Transactional
    @Override
    public Order submitOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PAYED);
        return orderRepository.save(order);
    }

    @Transactional
    @Override
    public Order cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        orderItemRepository.deleteAll(order.getOrderItems());
        orderRepository.delete(order);
        return order;
    }
}
