package smart.tech.com.SmartTech.services;

import smart.tech.com.SmartTech.model.domain.Order;
import smart.tech.com.SmartTech.model.domain.ShoppingCart;
import smart.tech.com.SmartTech.model.domain.User;

import java.time.LocalDateTime;

public interface UserService {

     User findByUsername(String username);

     User register(String username, String firstName, String lastName, String phoneNumber, String email,
                   String password, String confirmPassword);

     User editUser(String username, String firstName, String lastName, String phoneNumber, String email, String password, String confirmPassword);



}
