package smart.tech.com.SmartTech.services.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;
import smart.tech.com.SmartTech.model.DTO.LoginResponseDTO;
import smart.tech.com.SmartTech.model.DTO.UserDTO;
import smart.tech.com.SmartTech.model.domain.User;

import java.util.Optional;

public interface UserService extends UserDetailsService {

     User findByUsername(String username);

     Optional<User> register(UserDTO userDTO);

     User login(UserDTO userDTO);

     Optional<User> editUser(String username, UserDTO userDTO);

     Optional<LoginResponseDTO> createToken (UserDTO userDTO);
}
