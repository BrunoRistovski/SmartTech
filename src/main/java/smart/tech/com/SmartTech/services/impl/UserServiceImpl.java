package smart.tech.com.SmartTech.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart.tech.com.SmartTech.model.DTO.UserDTO;
import smart.tech.com.SmartTech.model.domain.*;
import smart.tech.com.SmartTech.model.enumerations.Role;
import smart.tech.com.SmartTech.model.exceptions.InvalidEmailException;
import smart.tech.com.SmartTech.model.exceptions.InvalidPasswordException;
import smart.tech.com.SmartTech.model.exceptions.InvalidUsernameException;
import smart.tech.com.SmartTech.model.exceptions.UserNotFoundException;
import smart.tech.com.SmartTech.repository.ShoppingCartRepository;
import smart.tech.com.SmartTech.repository.UserRepository;
import smart.tech.com.SmartTech.services.interfaces.UserService;

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
    public User register(UserDTO userDTO) {

        if(userRepository.findById(userDTO.getUsername()).isPresent()) {
            throw new InvalidUsernameException(userDTO.getUsername());
        }
        ExceptionFunction(userDTO);

        List<ShoppingCartItem> shoppingCartItems = new ArrayList<>() ;
        ShoppingCart shoppingCart = new ShoppingCart(LocalDateTime.now(),0.0,shoppingCartItems);
        shoppingCartRepository.save(shoppingCart);
        List<Order> orders = new ArrayList<>() ;

        User user = new User(userDTO.getUsername(),userDTO.getFirstName(),userDTO.getLastName(),LocalDateTime.now(),userDTO.getPhoneNumber(),
                userDTO.getEmail(),userDTO.getPassword(),Role.USER,shoppingCart,orders);

        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);

        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User editUser(String username, UserDTO userDTO) {

        User user = findByUsername(username);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }

    public void ExceptionFunction(UserDTO userDTO) {

        if (userRepository.findByEmail(userDTO.getEmail()).isPresent())
            throw new InvalidEmailException(userDTO.getEmail());

        if(!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new InvalidPasswordException();
        }
    }

}
