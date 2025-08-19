package smart.tech.com.SmartTech.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart.tech.com.SmartTech.model.domain.*;
import smart.tech.com.SmartTech.model.enumerations.Role;
import smart.tech.com.SmartTech.model.exceptions.InvalidEmailException;
import smart.tech.com.SmartTech.model.exceptions.InvalidPasswordException;
import smart.tech.com.SmartTech.model.exceptions.InvalidUsernameException;
import smart.tech.com.SmartTech.model.exceptions.UserNotFoundException;
import smart.tech.com.SmartTech.repository.ShoppingCartRepository;
import smart.tech.com.SmartTech.repository.UserRepository;
import smart.tech.com.SmartTech.services.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public UserServiceImpl(UserRepository userRepository, ShoppingCartRepository shoppingCartRepository) {
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findById(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Transactional
    @Override
    public User register(String username, String firstName, String lastName, String phoneNumber, String email, String password, String confirmPassword) {
        if(userRepository.findById(username).isPresent()) {
            throw new InvalidUsernameException(username);
        }

        if(userRepository.findUserByEmail(email) != null)
            throw new InvalidEmailException(email);

        if(password.equals(confirmPassword)) {
            throw new InvalidPasswordException();
        }

        List<ShoppingCartItem> shoppingCartItems = new ArrayList<>() ;
        ShoppingCart shoppingCart = new ShoppingCart(LocalDateTime.now(),0.0,shoppingCartItems);
        shoppingCartRepository.save(shoppingCart);
        List<Order> orders = new ArrayList<>() ;

        User user = new User(username,firstName,lastName,LocalDateTime.now(),phoneNumber, email,password,Role.USER,shoppingCart,orders);

        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);

        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User editUser(String username, String firstName, String lastName, String phoneNumber, String email, String password, String confirmPassword) {
        if(userRepository.findById(username).isPresent()) {
            throw new InvalidUsernameException(username);
        }

        if(userRepository.findUserByEmail(email) != null)
            throw new InvalidEmailException(email);

        if(password.equals(confirmPassword)) {
            throw new InvalidPasswordException();
        }
        User user = findByUsername(username);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        user.setPassword(password);
        return userRepository.save(user);
    }
}
