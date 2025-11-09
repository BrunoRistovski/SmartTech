package smart.tech.com.SmartTech.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart.tech.com.SmartTech.model.DTO.OrderDTO;
import smart.tech.com.SmartTech.model.DTO.OrderItemDTO;
import smart.tech.com.SmartTech.model.domain.*;
import smart.tech.com.SmartTech.model.enumerations.OrderStatus;
import smart.tech.com.SmartTech.model.exceptions.OrderNotFoundException;
import smart.tech.com.SmartTech.repository.*;
import smart.tech.com.SmartTech.services.interfaces.OrderItemService;
import smart.tech.com.SmartTech.services.interfaces.OrderService;
import smart.tech.com.SmartTech.services.interfaces.ShoppingCartItemService;
import smart.tech.com.SmartTech.services.interfaces.UserService;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderItemRepository orderItemRepository;
    private final ShoppingCartItemService shoppingCartItemService;
    private final OrderItemService orderItemService;
    private final MailService mailService;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, OrderItemRepository orderItemRepository, ShoppingCartItemRepository shoppingCartItemRepository, ShoppingCartItemService shoppingCartItemService, OrderItemService orderItemService, MailService mailService, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.orderItemRepository = orderItemRepository;
        this.shoppingCartItemService = shoppingCartItemService;
        this.orderItemService = orderItemService;
        this.mailService = mailService;
        this.productRepository = productRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(long orderId) {
        return Optional.of(orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new));
    }

    @Transactional
    @Override
    public Optional<Order> createOrder(OrderDTO orderDTO) {

        List<OrderItem> orderItems = new ArrayList<>();
        User user = userService.findByUsername(orderDTO.getUsername());

        Order order = new Order(OrderStatus.CREATED, orderDTO.getAddress(), orderDTO.getCity(), orderDTO.getZipcode(), LocalDateTime.now(), 0.0, user, orderItems);

        orderRepository.save(order);

        ShoppingCart shoppingcart = user.getShoppingCart();
        List<ShoppingCartItem> shoppingCartItems = shoppingcart.getShoppingCartItems();
        List<ShoppingCartItem> itemsToDelete = new ArrayList<>(shoppingCartItems);

        for (ShoppingCartItem shoppingCartItem : itemsToDelete) {
            OrderItemDTO orderItemDTO = new OrderItemDTO(order.getId(), shoppingCartItem.getProduct().getId(), shoppingCartItem.getQuantity());
            orderItemService.createOrderItem(orderItemDTO);

            shoppingcart.getShoppingCartItems().remove(shoppingCartItem);
            shoppingCartItemService.deleteShoppingCartItem(shoppingCartItem.getId());
        }

        orderRepository.save(order);

        return Optional.of(order);
    }

    @Transactional
    @Override
    public Optional<Order> submitOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PAYED);

        List<OrderItem> orderItems = orderItemRepository.findAll();
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getOrder().getId().equals(orderId)){
                Product product = orderItem.getProduct();
                product.setStockQuantity(product.getStockQuantity() - orderItem.getQuantity());
                productRepository.save(product);
            }
        }

        orderRepository.save(order);

        StringBuilder emailText = new StringBuilder();
        emailText.append("Hi ").append(order.getUser().getUsername()).append(".\n");
        emailText.append("Thank you for order!\n");
        emailText.append("Order details: \n");
        for(OrderItem orderItem : order.getOrderItems()) {
            emailText.append(orderItem.getProduct().getName()).append(" - Quantity: ").append(orderItem.getQuantity()).append("\n");
        }
        emailText.append("Total Price : ").append(order.getTotalAmount()).append("\n");
        mailService.sendEmail(order.getUser().getEmail(),"Your Order Confirmation",emailText.toString());

        return Optional.of(order);
    }

    @Transactional
    @Override
    public Optional<Order> cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        orderItemRepository.deleteAll(order.getOrderItems());
        orderRepository.delete(order);

        return Optional.of(order);
    }
}
