package smart.tech.com.SmartTech.services.interfaces;

import smart.tech.com.SmartTech.model.DTO.UserDTO;
import smart.tech.com.SmartTech.model.domain.User;

public interface UserService {

     User findByUsername(String username);

     User register(UserDTO userDTO);

     User editUser(String username, UserDTO userDTO);



}
