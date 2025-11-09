package smart.tech.com.SmartTech.services.impl;

import com.stripe.model.tax.Registration;
import org.springframework.mail.MailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart.tech.com.SmartTech.JWT.util.JwtUtil;
import smart.tech.com.SmartTech.model.DTO.LoginResponseDTO;
import smart.tech.com.SmartTech.model.DTO.UserDTO;
import smart.tech.com.SmartTech.model.domain.*;
import smart.tech.com.SmartTech.model.enumerations.Role;
import smart.tech.com.SmartTech.model.exceptions.*;
import smart.tech.com.SmartTech.repository.ShoppingCartRepository;
import smart.tech.com.SmartTech.repository.UserRepository;
import smart.tech.com.SmartTech.services.interfaces.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository, ShoppingCartRepository shoppingCartRepository, MailService mailService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Transactional
    @Override
    public Optional<User> register(UserDTO userDTO) {

        if(userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new InvalidUsernameException(userDTO.getUsername());
        }
        ExceptionFunction(userDTO);

        List<ShoppingCartItem> shoppingCartItems = new ArrayList<>() ;
        ShoppingCart shoppingCart = new ShoppingCart(LocalDateTime.now(),0.0,shoppingCartItems);
        shoppingCartRepository.save(shoppingCart);
        List<Order> orders = new ArrayList<>() ;

        User user = new User(userDTO.getUsername(),userDTO.getFirstName(),userDTO.getLastName(),LocalDateTime.now(),userDTO.getPhoneNumber(),
                userDTO.getEmail(), passwordEncoder.encode(userDTO.getPassword()), Role.ROLE_USER,shoppingCart,orders);

        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
        userRepository.save(user);
        mailService.sendEmail(user.getEmail(),"SmartTech account registration","Welcome to SmartTech dear " + user.getUsername());

        return Optional.of(user);
    }

    @Transactional
    @Override
    public User login(UserDTO userDTO) {

        User user = userRepository.findByUsername(userDTO.getUsername()).orElseThrow(InvalidCredentialsException::new);
        if(!passwordEncoder.matches(userDTO.getPassword(),user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return user;
    }

    @Transactional
    @Override
    public Optional<User> editUser(String username, UserDTO userDTO) {

        User user = findByUsername(username);
        user.setFirstName(userDTO.getFirstName() != null ? userDTO.getFirstName() : user.getFirstName());
        user.setLastName(userDTO.getLastName() != null ? userDTO.getLastName() : user.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber() != null ? userDTO.getPhoneNumber() : user.getPhoneNumber());
        user.setEmail(userDTO.getEmail() != null ? userDTO.getEmail() : user.getEmail());
        user.setPassword(userDTO.getPassword()!= null ? passwordEncoder.encode(userDTO.getPassword()) : user.getPassword());


        userRepository.save(user);

        return Optional.of(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Transactional
    @Override
    public Optional<LoginResponseDTO> createToken (UserDTO userDTO){

        User user = login(userDTO);
        String token = jwtUtil.generateToken(user);

        return Optional.of(new LoginResponseDTO(token));
    }

    public void ExceptionFunction(UserDTO userDTO) {

        if (userRepository.findByEmail(userDTO.getEmail()).isPresent())
            throw new InvalidEmailException(userDTO.getEmail());

        if(!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new InvalidPasswordException();
        }
    }
}
